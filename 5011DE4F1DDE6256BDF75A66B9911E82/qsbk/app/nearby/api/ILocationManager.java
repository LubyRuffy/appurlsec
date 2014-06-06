package qsbk.app.nearby.api;

public interface ILocationManager {
    public int getLocation(ILocationCallback r1_ILocationCallback);

    public void reinit();

    public void stop();
}