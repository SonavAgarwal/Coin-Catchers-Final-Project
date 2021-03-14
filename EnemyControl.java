public class EnemyControl implements Runnable {

    Enemy enemy;
    boolean lastMoveSucceeded = false;
    int lastMoveDirection = 0;

    public EnemyControl(Enemy e) {
        enemy = e;
    }

    public void run() {
        try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        while (enemy.isAlive()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (enemy.getPosition(). getDistanceFrom(PublicReference.getPlayer().getPosition()) < 5) {
                PublicReference.getWorld().removeEnemyFaces(enemy);

                Position ePos = enemy.getPosition();
                Position pPos = PublicReference.getPlayer().getPosition();
                if (lastMoveDirection == 0 || lastMoveDirection == 2) {
                        if (ePos.getX() > pPos.getX()) {
                            lastMoveSucceeded = enemy.moveX(-1);
                            lastMoveDirection = 3;
                        } else {
                            lastMoveSucceeded = enemy.moveX(1);
                            lastMoveDirection = 1;
                        }
                    } else if (lastMoveDirection == 1 || lastMoveDirection == 3) {
                        if (ePos.getZ() > pPos.getZ()) {
                            lastMoveSucceeded = enemy.moveZ(-1);
                            lastMoveDirection = 2;
                        } else {
                            lastMoveSucceeded = enemy.moveZ(1);
                            lastMoveDirection = 0;
                        }
                    }
                // if (lastMoveSucceeded) {
                //     if (lastMoveDirection == 0) {
                //         lastMoveSucceeded = enemy.moveZ(1);
                //     } else if (lastMoveDirection == 1) {
                //         lastMoveSucceeded = enemy.moveX(1);
                //     } else if (lastMoveDirection == 2) {
                //         lastMoveSucceeded = enemy.moveZ(-1);
                //     } else if (lastMoveDirection == 3) {
                //         lastMoveSucceeded = enemy.moveX(-1);
                //     }
                // } else {
                //     if (lastMoveDirection == 0 || lastMoveDirection == 2) {
                //         if (ePos.getX() > pPos.getX()) {
                //             lastMoveSucceeded = enemy.moveX(-1);
                //             lastMoveDirection = 3;
                //         } else {
                //             lastMoveSucceeded = enemy.moveX(1);
                //             lastMoveDirection = 1;
                //         }
                //     } else if (lastMoveDirection == 1 || lastMoveDirection == 3) {
                //         if (ePos.getZ() > pPos.getZ()) {
                //             lastMoveSucceeded = enemy.moveZ(-1);
                //             lastMoveDirection = 2;
                //         } else {
                //             lastMoveSucceeded = enemy.moveZ(1);
                //             lastMoveDirection = 0;
                //         }
                //     }
                // }

                PublicReference.getWorld().addEnemyFaces(enemy);
            } else if (Math.random() < 0.3) {
                PublicReference.getWorld().removeEnemyFaces(enemy);
                if (Math.random() > 0.499) {
                    if (Math.random() > 0.499) {
                        enemy.moveX(-1);
                    } else {
                        enemy.moveX(1);
                    }
                } else {
                    if (Math.random() > 0.499) {
                        enemy.moveZ(-1);
                    } else {
                        enemy.moveZ(1);
                    }
                }
                
                
                PublicReference.getWorld().addEnemyFaces(enemy);
            }
            

            enemy.update();
        }
    }
}