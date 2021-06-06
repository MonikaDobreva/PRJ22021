///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package frontend;
//
//import java.io.IOException;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.stage.Stage;
//import static frontend.UIHelpers.pause;
//import static frontend.UIHelpers.printChildren;
//import static org.assertj.core.api.Assertions.*;
//import org.testfx.api.FxRobot;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.testfx.framework.junit5.ApplicationExtension;
//import org.testfx.framework.junit5.Start;
//
///**
// *
// * @author Pieter van den Hombergh {@code pieter.van.den.hombergh@gmail.com}
// */
//@ExtendWith( ApplicationExtension.class )
//public class SwitcherTest {
//
//    static {
//        if ( Boolean.getBoolean( "SERVER" ) ) {
//            System.setProperty( "java.awt.headless", "true" );
//            System.setProperty( "testfx.robot", "glass" );
//            System.setProperty( "testfx.headless", "true" );
//            System.setProperty( "prism.order", "sw" );
//            System.setProperty( "prism.text", "t2k" );
//            System.setProperty( "glass.platform", "Monocle" );
//            System.setProperty( "monocle.platform", "Headless" );
//        }
//    }
//
//    // set by start
//    Switcher app;
//    // set by start
//    Stage stage;
//
//    @Start
//    void start( Stage stage ) throws IOException {
//        app = new Switcher();
//        app.start( stage );
//        this.stage=stage;
//    }
//
//    //@Disabled("think TDD")
//    @Test
//    public void tSwitch() {
//
//        Parent root = stage.getScene().getRoot();
//        printChildren( root );
//        FxRobot rob = new FxRobot();
//
//        for ( int i = 0; i < 3; i++ ) {
//
//            rob.clickOn("#cb1"); // <1>
//            Button b = (Button) rob.lookup( "#primaryButton" ).query(); //<2>
//            rob.clickOn( b ); //<3> 
//            pause( 1000 );  //<4>
//            b = (Button) rob.lookup( "#secondaryButton" ).query();
//            rob.clickOn("#cb1");
//            rob.clickOn( b );
//            pause( 1000 );
//        }
//        fail( "method method reached end. You know what to do." );
//    }
//}
//
