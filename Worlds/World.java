import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
// import java.util.ArrayList;

import java.awt.*;

// import java.lang.ClassNotFoundException;

public class World {
    private Player player;

    private HashMap<Position, Cube> cubes;
    private ArrayList<Position> cubePositions;
    private HashMap<Position, CubeFace> cubeFaces;
    private ArrayList<Position> cubeFacePositions;
    private int[] dimensions;

    public World (String dataFile, Player player) {
        this.player = player;
        try {
            cubes = new HashMap<Position, Cube>();
            cubePositions = new ArrayList<Position>();
            cubeFaces = new HashMap<Position, CubeFace>();
            Scanner dataInput = new Scanner(new File("./Worlds/" + dataFile + ".txt"));
            String[] stringDimensions = dataInput.nextLine().split(" ");
            dimensions = new int[]{Integer.parseInt(stringDimensions[0]), Integer.parseInt(stringDimensions[1]), Integer.parseInt(stringDimensions[2])};
            for (int y = 0; y < dimensions[1]; y++) {
                for (int z = 0; z < dimensions[2]; z++) {
                    for (int x = 0; x < dimensions[0]; x++) {
                        String className = dataInput.next() + "Cube";
                        Position pos = new Position(x, y * PublicReference.halfCubeHeight * 2, z);
                        Cube c = null;
                        try {
                            c = Cube.class.cast(Class.forName(className).getConstructor(Position.class).newInstance(pos));
                        } catch (Exception exc) {
                            exc.printStackTrace();
                            System.out.println(className + " not found");
                        }
                        
                        cubes.put(pos, c);
                        cubePositions.add(pos);
                    }
                }
            }

            
            // System.out.println(cubes);
            // System.out.println(cubeFaces);

            // System.out.println("after");
            
            // updateFaceDistances();
            for (Position p : cubes.keySet()) {
                for (CubeFace f : cubes.get(p).getFaces()) {
                    // System.out.println("We got a face");
                    addFace(f);
                }
            }

            cubeFacePositions = new ArrayList<>(cubeFaces.keySet());
            // updateFaceDistances();
            // System.out.println(cubeFacePositions);
            Collections.sort(cubeFacePositions);

            // System.out.println("done");
            // System.out.println(cubes);
            // System.out.println(cubeFaces);


            // Collections.sort(tilePositionsCenterSorted);
            // Collections.sort(tilePositions, Comparator.comparing(tp -> tp.getZ()));
            // Collections.sort(tilePositionsCenterSorted, Comparator.comparing(tp -> tp.getDistFrom(player.getPosition())));

        } catch (FileNotFoundException fnfe) {
            System.out.println("World " + dataFile + " not found");
        }
        
    }

    public void addFace(CubeFace f) {
        if (f.getColor() == null) return;
        // System.out.println("Adding face " + f);
        // System.out.println(cubeFaces.keySet());
        if (cubeFaces.containsKey(f.getPosition())) {
            // boolean f2trans = cubeFaces.get(f.getPosition()).getCube().isTransparent();
            // boolean f1trans = f.getCube().isTransparent();
            // System.out.println(f1trans + " and " + f2trans);
            // if (f1trans && f2trans) {
            //     System.out.println("option 1");
            //     cubeFaces.remove(f.getPosition());
            // } else if (f1trans) { //potential cube is transparent
            //     System.out.println("option 2");
            //     // return;
            // } else if (f2trans) { //cube in map is transparent
            //     // cubeFaces.remove(f.getPosition());
            //     System.out.println("option 3");
            //     cubeFaces.put(f.getPosition(), f);
            // } else {
            //     System.out.println("option 4");
            //     cubeFaces.remove(f.getPosition());
            // }
            cubeFaces.remove(f.getPosition());

            // System.out.println("removed");
            
        } else {
            // System.out.println("added");
            cubeFaces.put(f.getPosition(), f);
        }

        

        // System.out.println();
        // System.out.println(cubeFaces);
    }

    public void updateFaceDistances() {
        // System.out.println(cubeFaces);
        for (Position p : cubeFacePositions) { //originally cubefaces.keyset cubeFaces.keySet()
            // System.out.println("--------");
            // System.out.println(cubeFaces.containsKey(p));
            // System.out.println(p);
            // System.out.println(cubeFaces.get(p));
            // System.out.println(cubeFaces.get(p).getPosition());
            // System.out.println("++++++++");
            cubeFaces.get(p).getPosition().setDistanceFromCamera(player.getCameraPosition());
        }
    }

    public void render(Graphics g) {

        // for (Position p : cubeFaces.keySet()) {
        //     System.out.println("drawing a cubeface");
        //     CubeFace f = cubeFaces.get(p);
        //     System.out.println(f);
        //     System.out.println(player.getCameraPosition());
        //     System.out.println(player.getCameraRotation());


        //     // System.out.println(f.render)
        //     cubeFaces.get(p).render(g, player.getCameraPosition(), player.getCameraRotation());
        // }

        updateFaceDistances();
        Collections.sort(cubeFacePositions);

        // System.out.println(cubeFacePositions);

        for (Position p : cubeFacePositions) {
            // System.out.println("drawing a cubeface");
            cubeFaces.get(p).render(g, player.getCameraPosition(), player.getCameraRotation());
        }

        // for (Position p : tilePositionsCenterSorted) {
        //     tiles.get(p).renderSides(g, player.getCameraPosition());
        // }

        // // System.out.println(tilePositions);
        // for (Position p : tilePositions) {
        //     tiles.get(p).renderTop(g, player.getCameraPosition());
        // }

    }
}