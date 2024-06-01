package com.capgemini.wsb.fitnesstracker.training.internal;

/**
 * Enum representing various types of physical activities.
 */
public enum ActivityType {

    /**
     * Running activity.
     */
    RUNNING("Running"),

    /**
     * Cycling activity.
     */
    CYCLING("Cycling"),

    /**
     * Walking activity.
     */
    WALKING("Walking"),

    /**
     * Swimming activity.
     */
    SWIMMING("Swimming"),

    /**
     * Tennis activity.
     */
    TENNIS("Tennis");

    private final String displayName;

    /**
     * Constructor for the ActivityType enum.
     *
     * @param displayName The display name of the activity type.
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the display name of the activity type.
     *
     * @return The display name of the activity type.
     */
    public String getDisplayName() {
        return displayName;
    }
}
