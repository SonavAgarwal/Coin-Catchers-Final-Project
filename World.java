import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class World {
    private Player player;

    private HashMap<Position, Cube> cubes;
    private ArrayList<Position> cubePositions;
    private HashMap<CubePosition, CubeFace> cubeFaces;
    private ArrayList<CubePosition> cubeFacePositions;
    private HashMap<CubePosition, Item> items = new HashMap<CubePosition, Item>();
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private HashMap<Position, Spike> obstacles = new HashMap<Position, Spike>();
    private int[] dimensions;

    private int quix = 0, quiy = 0;

    public World (String dataFile, Player player) {

        this.player = player;

        //load data
        try {
            cubes = new HashMap<Position, Cube>();
            cubePositions = new ArrayList<Position>();
            cubeFaces = new HashMap<CubePosition, CubeFace>();
            Scanner dataInput = new Scanner(new File("./Worlds/" + dataFile + ".txt"));
            String[] stringDimensions = dataInput.nextLine().split(" ");
            dimensions = new int[]{Integer.parseInt(stringDimensions[0]), Integer.parseInt(stringDimensions[1]), Integer.parseInt(stringDimensions[2])};
            for (int y = 0; y < dimensions[1]; y++) {
                for (int z = 0; z < dimensions[2]; z++) {
                    for (int x = 0; x < dimensions[0]; x++) {
                        String cubeName = dataInput.next();
                        if (cubeName.equals("Empty")) continue;
                        Position pos = new Position(x, y * PublicReference.halfCubeHeight * 2, z);
                        Cube c = createCube(cubeName, pos);
                        
                        cubes.put(pos, c);
                        cubePositions.add(pos);
                    }
                }
            }


            //add coins
            for (int i = 0; i < 50; i++) {
                boolean failed = true;
                do {
                    Position itemPos = new Position((int) (Math.random() * 50), PublicReference.halfCubeHeight * 6, (int) (Math.random() * 50));
                    if (!cubes.containsKey(itemPos)) {
                        Position groundCubePos = itemPos.copy().changeY(PublicReference.halfCubeHeight * -2);
                        groundCubePos.round();
                        // System.out.println(groundCubePos);
                        if (cubes.containsKey(groundCubePos)) {
                            if (cubes.get(groundCubePos).getCubeType() == 0) {
                                CubePosition finItemPos = itemPos.createCubePosition(-4);
                                finItemPos.changeX(0.5).changeZ(0.5);
                                items.put(finItemPos, new Item("Coin.png", finItemPos));
                                failed = false;
                            }
                        }
                    }
                } while (failed);
            }

            //add enemies
            for (int i = 0; i < 25; i++) {
                boolean failed = true;
                do {
                    Position enemyPos = new Position((int) (Math.random() * 50), PublicReference.halfCubeHeight * 6, (int) (Math.random() * 50));
                    if (!cubes.containsKey(enemyPos)) {
                        Position groundCubePos = enemyPos.copy().changeY(PublicReference.halfCubeHeight * -2);
                        groundCubePos.round();
                        if (cubes.containsKey(groundCubePos)) {
                            if (cubes.get(groundCubePos).getCubeType() == 0) {
                                enemies.add(new Enemy(enemyPos));
                                failed = false;
                            }
                        }
                    }
                } while (failed);
            }

            //add spikes
            for (int i = 0; i < 25; i++) {
                boolean failed = true;
                do {
                    Position obstaclePos = new Position((int) (Math.random() * 50), PublicReference.halfCubeHeight * 6, (int) (Math.random() * 50));
                    if (!cubes.containsKey(obstaclePos)) {
                        Position groundCubePos = obstaclePos.copy().changeY(PublicReference.halfCubeHeight * -2);
                        groundCubePos.round();
                        if (cubes.containsKey(groundCubePos)) {
                            if (cubes.get(groundCubePos).getCubeType() == 0) {
                                obstacles.put(obstaclePos, new Spike(obstaclePos));
                                failed = false;
                            }
                        }
                    }
                } while (failed);
            }


            getFaces();

            cubeFacePositions = new ArrayList<>(cubeFaces.keySet());
            Collections.sort(cubeFacePositions);

        } catch (FileNotFoundException fnfe) {
            System.out.println("World " + dataFile + " not found");
        }
        
    }

    public void getFaces() {
        cubeFaces.clear();
        for (Position p : cubes.keySet()) {
            addCubeFaces(cubes.get(p));
        }
        for (Enemy e : enemies) {
            addCubeFaces(e);
        }
        for (Position p : obstacles.keySet()) {
            addCubeFaces(obstacles.get(p));
        }
        for (Position p : items.keySet()) {
            addFace(items.get(p));
        }
    }

    public void removeEnemyFaces(Enemy e) {
        for (CubeFace f : e.getFaces()) {
            cubeFaces.remove(f.getPosition());
        }
    }

    public void addEnemyFaces(Enemy e) {
        addCubeFaces(e);
    }

    public Cube createCube(String name, Position p) {
        String className = name + "Cube";
        Cube c = null;
        try {
            c = Cube.class.cast(Class.forName(className).getConstructor(Position.class).newInstance(p));
        } catch (Exception exc) {
            exc.printStackTrace();
            System.out.println(className + " not found");
        }
        return c;
    }

    public void addCubeFaces(Cube c) {
        if (c == null) return;
        for (CubeFace f : c.getFaces()) {
            addFace(f);
        }
    }

    public void addFace(CubeFace f) {
        if (f.getColor() == null) return;
        if (cubeFaces.containsKey(f.getPosition())) {
            cubeFaces.remove(f.getPosition());            
        } else {
            cubeFaces.put(f.getPosition(), f);
        }

    }

    public void updateFaceDistances() {
        for (CubePosition p : cubeFacePositions) {
            if (cubeFaces.get(p) != null)
            cubeFaces.get(p).getPosition().setDistanceFromCamera(player.getCameraPosition());
        }
    }

    public void update() {
        CubePosition playerPos = player.getPosition().createCubePosition(-4);
        // System.out.println(playerPos);
        // System.out.println(items);
        if (items.containsKey(playerPos)) {
            removeFace(items.remove(playerPos));
        }
        if (obstacles.containsKey(player.getCornerPosition())) {
            player.kill();
        }
    }

    public CubePosition screenToWorldCoords(int x, int y) {
        for (double sliceLevel = 10; sliceLevel > 0; sliceLevel--) {
            double testY = sliceLevel * 2 * PublicReference.halfCubeHeight;
            double worldX = ((x - 500) * (player.getCameraPosition().getY() - testY) + player.getCameraPosition().getX()) / -30 + player.getPosition().getX();
            double worldZ = ((y - 375) * (player.getCameraPosition().getY() - testY) + player.getCameraPosition().getZ()) / -30 + player.getPosition().getZ();
            CubePosition cp = new CubePosition(Math.floor(worldX + 0.1) + 0.5, testY, Math.floor(worldZ + 0.1) + 0.5, 0);
            cp.round();
            if (cubeFaces.containsKey(cp)) {
                return cp;
            }
        }
        return null;
    }

    public void highlightMouse(int x, int y) {

        CubePosition cp = screenToWorldCoords(x, y);
        if (cp != null)
        cubeFaces.get(cp).highlight();

    }

    public void interpretMouseClick(MouseEvent mE) {
        int x = mE.getX();
        int y = mE.getY();

        Cube targetCube = null;
        
        CubeFace cf = cubeFaces.get(screenToWorldCoords(x, y));
        if (cf != null)
        targetCube = cf.getCube();

        if (targetCube != null) {
            if (mE.getButton() == MouseEvent.BUTTON3) {
                //left click
                Position newPos = targetCube.getPosition().copy().changeY(PublicReference.halfCubeHeight * 2);
                newPos.round();
                System.out.println("new pos: " + newPos);
                cubes.put(newPos, createCube(player.getSelectedBlockName(), newPos));
                addCubeFaces(cubes.get(newPos));
                regenerateCubeFacePositions();
                // System.out.println(cubes);
                System.out.println(cubes.containsKey(newPos));
            } else if (mE.getButton() == MouseEvent.BUTTON1) {
                //right click
                Position pos = targetCube.getPosition().copy();
                cubes.remove(pos);
                cubeFaces.clear();
                getFaces();
                regenerateCubeFacePositions();
            }
        }
    }

    public void regenerateCubeFacePositions() {
        cubeFacePositions = new ArrayList<>(cubeFaces.keySet());
        updateFaceDistances();
        Collections.sort(cubeFacePositions);
    }

    public Cube getCubeAt(Position p) {
        return cubes.get(p);
    }

    public boolean hasObstacleAt(Position p) {
        if (obstacles.containsKey(p)) return true;
        return false;
    }

    public void removeEnemy(Enemy e) {
        removeEnemyFaces(e);
        enemies.remove(e);
    }

    public void saveWorld() {
        System.out.println("Saving");
        System.out.println(cubes.containsKey(new Position(0, 0.3, 0)));
        try {
            File worldFile = new File("Worlds/GameMap.txt");

            worldFile.createNewFile();
        } catch (IOException e) {
            System.out.println("World was not created.");
            e.printStackTrace();
        }

        try {
            FileWriter worldWriter = new FileWriter("Worlds/GameMap.txt");
            String worldString = "";
            worldString += "51 10 51\n";
            for (int y = 0; y < 10; y++) {
                for (int z = 0; z < 51; z++) {
                    for (int x = 0; x < 51; x++) {
                        Position p = new Position(x, y * PublicReference.halfCubeHeight * 2, z);
                        p.round();
                        Cube c = cubes.get(p);
                        if (c == null) {
                            worldString += "Empty";
                        } else {
                            worldString += c.getName();
                        }
                        worldString += " ";
                    }
                }
            }
            worldWriter.write(worldString);
            worldWriter.close();
            System.out.println("Successfully saved");
        } catch (Exception e) {
            System.out.println("World was not written.");
            e.printStackTrace();
        }
    }

    public void removeFace(CubeFace f) {
        cubeFaces.remove(f.getPosition());
    }

    public int getItemsRemaining() {
        return items.size();
    }

    public void render(Graphics g) {
        
        //sort faces from farthest to nearest
        updateFaceDistances();
        Collections.sort(cubeFacePositions);

        //draw faces
        for (CubePosition p : cubeFacePositions) {
            if (cubeFaces.get(p) != null)
                cubeFaces.get(p).render(g, player.getCameraPosition());
        }
        
    }
}