package solutions.segorus.ofbiz.quickstart.wizard.steps.distribution

import org.apache.tomcat.util.http.fileupload.FileUtils
import solutions.segorus.ofbiz.quickstart.CmdResult
import solutions.segorus.ofbiz.quickstart.CommandUtils
import solutions.segorus.ofbiz.quickstart.wizard.StepState
import solutions.segorus.ofbiz.quickstart.wizard.WizardStepApplier

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

class DistributionSelectionApplier implements WizardStepApplier<DistributionSelectionConfig> {
    private StepState.Status downloadStatus = StepState.Status.SCHEDULED
    private String downloadDetails = ""
    private float downloadProgress = 0

    private StepState.Status extractStatus = StepState.Status.SCHEDULED
    private String extractDetails = ""
    private float extractProgress = 0

    private StepState.Status gradleWrapperStatus = StepState.Status.SCHEDULED
    private String gradleWrapperDetails = ""
    private float gradleWrapperProgress = 0

    synchronized List<StepState> getStates() {
        return new StepState[] {
                new StepState(
                        downloadStatus,
                        "Download OFBIZ archive",
                        downloadDetails,
                        downloadProgress
                ),
                new StepState(
                        extractStatus,
                        "Extract OFBIZ archive",
                        extractDetails,
                        extractProgress
                ),
                new StepState(
                        gradleWrapperStatus,
                        "Configure gradle",
                        gradleWrapperDetails,
                        gradleWrapperProgress
                ),
        }
    }

    void run(DistributionSelectionConfig config)
    {
        try {
            synchronized (this) {
                downloadStatus = StepState.Status.IN_PROGRESS
                downloadProgress = 0
                downloadDetails = "Downloading from " + config.getSource() + " ..."
            }
            URL url = new URL(config.getSource());
            String downloadedFilePath = "OFBIZ.zip"
            HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
            long completeFileSize = httpConnection.getContentLength();
            println("File size: " + completeFileSize.toString())

            FileOutputStream fileOutputStream = new FileOutputStream(downloadedFilePath);

            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpConnection.getInputStream());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 1024);

            byte[] data = new byte[1024];
            long downloadedFileSize = 0;
            int x = 0;
            while ((x = bufferedInputStream.read(data, 0, 1024)) >= 0) {
                downloadedFileSize += x;

                // calculate progress

                synchronized (this) {
                    downloadProgress = ((double) downloadedFileSize) / ((double) completeFileSize);
                    println(downloadProgress)
                }

                bufferedOutputStream.write(data, 0, x);
            }
            bufferedOutputStream.close();
            bufferedInputStream.close();

            // Unziping

            synchronized (this) {
                downloadStatus = StepState.Status.COMPLETE
                downloadProgress = 1
                downloadDetails = ""
            }

            synchronized (this) {
                extractStatus = StepState.Status.IN_PROGRESS
                extractProgress = -1
                extractDetails = "Extracting packages ..."
            }

            File destinationDir = new File("install");

            FileUtils.cleanDirectory(destinationDir)

            byte[] buffer = new byte[1024];
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(downloadedFilePath));
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) {
                var filteredName = zipEntry.getName().replaceFirst("^[^/]+/", "")

                if (filteredName == "")
                {
                    //Skip root dir
                    zipEntry = zipInputStream.getNextEntry();
                    continue
                }
                File newFile = new File(destinationDir, filteredName);

                String destDirPath = destinationDir.getCanonicalPath();
                String destFilePath = newFile.getCanonicalPath();

                if (!destFilePath.startsWith(destDirPath + File.separator)) {
                    throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
                }

                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    // fix for Windows-created archives
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("Failed to create directory " + parent);
                    }

                    // write file content
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zipInputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zipEntry = zipInputStream.getNextEntry();
            }

            zipInputStream.closeEntry();
            zipInputStream.close();

            synchronized (this) {
                extractStatus = StepState.Status.COMPLETE
                extractProgress = 1
                extractDetails = ""
                gradleWrapperStatus = StepState.Status.IN_PROGRESS
                gradleWrapperProgress = -1
            }

            String overloadConf = "\nofbiz.config.overload.enable=true"
            Files.write(
                    Paths.get("install/framework/start/src/main/resources/org/apache/ofbiz/base/start/start.properties"),
                    overloadConf.getBytes(),
                    StandardOpenOption.APPEND
            );

            if (CommandUtils.isWindows())
            {
                CmdResult r1 = CommandUtils.runCmd("./gradle/init-gradle-wrapper.ps1");
                synchronized (this) {
                    gradleWrapperDetails += r1.stdout + r1.stderr
                }
            }
            else
            {
                CmdResult r1 = CommandUtils.runCmd("./gradle/init-gradle-wrapper.sh");
                synchronized (this) {
                    gradleWrapperDetails += r1.stdout + r1.stderr
                }
            }

            synchronized (this) {
                gradleWrapperStatus = StepState.Status.COMPLETE
                gradleWrapperProgress = 1
                gradleWrapperDetails = ""
            }


        }
//        catch (FileNotFoundException e) {
//            println(e)
//        } catch (IOException e) {
//            println(e)
//        }
        catch (Exception e) {
            println(e)
            synchronized (this) {
                downloadStatus = StepState.Status.ERROR
                downloadDetails = e.toString()
            }

        }
    }
}
