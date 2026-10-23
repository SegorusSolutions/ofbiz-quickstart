package solutions.segorus.ofbiz.quickstart.wizard

class StepState {
    enum Status {
        SCHEDULED("scheduled"),
        COMPLETE("complete"),
        IN_PROGRESS("in_progress"),
        ERROR("error");

        private String name;

        Status (String name)
        {
            this.name = name;
        }

        String getName()
        {
            return name;
        }
    }

    StepState(
            Status status,
            String title,
            String details,
            float progress
    )
    {
        this.status = status
        this.title = title
        this.details = details
        this.progress = progress
    }

    final Status status;
    final String title;
    final String details;
    final float progress;

}
