package frontend;

/**
 * A class that can switch to named scenes.
 * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
 */
interface RootSwitcher {

    /**
     * Set the root of the scene on the primary stage to the named scene
     * @param sceneName to use
     * @return the previous sceneName
     */
    String setRoot( String sceneName ) ;
    
}
