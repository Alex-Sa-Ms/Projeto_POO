public interface Events {

    String getEventDescription();

    float getTimeStamp();

    Events newEvent(String description, int timeOfTheEvent);
}
