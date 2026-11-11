package solutions.segorus.ofbiz.quickstart.wizard


import java.util.concurrent.atomic.AtomicBoolean

class Wizard {
    private LinkedHashMap<Class<WizardStep>, WizardStep> steps = new LinkedHashMap<>();
    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicBoolean complete = new AtomicBoolean(false);

    Wizard(List<WizardStep> steps)
    {
        for(step in steps)
        {
            this.steps.put(step.getClass(), step)
        }
    }

    Collection<WizardStep> getSteps() {
        return steps.values().asUnmodifiable()
    }

    <T> T getStep(Class<T> stepType) {
        return (T) steps.get(stepType)
    }

    List<StepState> getStepStates()
    {
        return getSteps().collectMany {it.getRunner().getStates() }
    }

    boolean isRunning()
    {
        return running
    }

    boolean isComplete()
    {
        return complete
    }

    void install()
    {
        AtomicBoolean r = this.running
        AtomicBoolean c = this.complete
        r.set(true);
        var steps = getSteps()
        Runnable downloadThread = new Runnable() {
            void run() {
                try {
                    for (step in steps)
                    {
                        step.getRunner().run(step.getConfig())
                    }
                }
                catch (Exception e) {
                    println(e)
                }
                finally {
                    r.set(false)
                    c.set(true)
                }
            }
        };
        new Thread(downloadThread).start();
    }
}
