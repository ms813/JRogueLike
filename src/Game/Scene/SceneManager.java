package Game.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Matthew on 07/04/2014.
 */
public class SceneManager {
    private static SceneManager instance = null;

    private Scene currentScene;
    private List<Scene> sceneList = new ArrayList<Scene>();

    protected SceneManager() {
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void createNewScene(String title, String tileSet, String level) {
        boolean exists = false;
        for (Scene s : sceneList) {
            if (s.getTitle().equals(title)) {
                System.out.println("Scene: " + title + " already exists!");
                exists = true;
            }
        }
        if (!exists) {
            Scene scene = new Scene(title);
            currentScene = scene;
            scene.generateMap(tileSet, level);
            sceneList.add(scene);
        }
    }

    public void setCurrentScene(String title) throws NoSuchElementException {
        Scene temp = null;
        for (Scene s : sceneList) {
            if (s.getTitle().equals(title)) {
                temp = s;
            }
        }
        if (temp == null) {
            throw new NoSuchElementException();
        } else {
            currentScene = temp;
        }
    }

    public void update() {
        currentScene.update();
    }

    public void printScenes() {
        System.out.println("[SceneManager.printScenes()] Listing current scenes: ");
        for (Scene scene : sceneList) {
            System.out.println(scene.getTitle());
        }
    }

    //defensive check to see if level sceneExists before creating it
    public boolean sceneExists(String title){
        for(Scene s : sceneList){
            if(s.getTitle().equals(title)){
                return true;
            }
        }
        return false;
    }

}
