public class PublicReference {
    public static Player player = new Player("Bob");
    public static World world = null;
    public static double halfCubeHeight = 0.05;

    public static boolean outline = false;

    public static void setPlayer(Player p) {
        player = p;
    }

    public static Player getPlayer() {
        return player;
    }

    public static World getWorld() {
        return world;
    }

    public static void setWorld(World w) {
        world = w;
    }
}