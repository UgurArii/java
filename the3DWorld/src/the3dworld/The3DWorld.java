
package the3dworld;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.animation.*;
import javafx.util.*;
import javafx.scene.transform.*;
import javafx.scene.image.*;


public class The3DWorld extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 800);
        
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-1000);
        camera.setNearClip(0.1);
        camera.setFarClip(2000.0);
        camera.setFieldOfView(35);
        scene.setCamera(camera);
        
        Cylinder cylinder = new Cylinder(100, 50);
        root.getChildren().add(cylinder);
        
        PhongMaterial blueStuff = new PhongMaterial();
        blueStuff.setDiffuseColor(Color.LIGHTBLUE);
        blueStuff.setSpecularColor(Color.BLUE);
        cylinder.setMaterial(blueStuff);
        
        cylinder.setTranslateX(-200);
        cylinder.setTranslateY(200);
        cylinder.setTranslateZ(200);
        
        Box box = new Box(100, 100, 100);
        box.setMaterial(blueStuff);
        box.setTranslateX(150);
        box.setTranslateY(-100);
        box.setTranslateZ(-100);
        root.getChildren().add(box);
        
        Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
        Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
        Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
        rxBox.setAngle(30);
        ryBox.setAngle(50);
        rzBox.setAngle(30);
        box.getTransforms().addAll(rxBox, ryBox, rzBox);
        
        Sphere sphere = new Sphere(100);
        sphere.setTranslateX(-180);
        sphere.setTranslateY(-100);
        sphere.setTranslateZ(100);
        root.getChildren().add(sphere);
        
        Image earthImage = new Image("file:E:\\zazaca\\Screenshots\\images.jpg");
        PhongMaterial earthPhong = new PhongMaterial();
        earthPhong.setDiffuseMap(earthImage);
        sphere.setMaterial(earthPhong);
        
        TriangleMesh pyramidMesh = new TriangleMesh();
        pyramidMesh.getTexCoords().addAll(0,0);
        float h = 150;
        
        float s = 300;
        pyramidMesh.getPoints().addAll(
        0,  0,  0,
        0,  h,  -s/2,
        -s/2,  h,  0,
        s/2,   h,  0,
        0,     h,  s/2                
        );
        pyramidMesh.getFaces().addAll(
0,0, 2,0, 1,0, // Front left face
0,0, 1,0, 3,0, // Front right face
0,0, 3,0, 4,0, // Back right face
0,0, 4,0, 2,0, // Back left face
4,0, 1,0, 2,0, // Bottom rear face
4,0, 3,0, 1,0 // Bottom front face
);
        
        
        MeshView pyramid = new MeshView(pyramidMesh);
        pyramid.setDrawMode(DrawMode.FILL);
        pyramid.setMaterial(blueStuff);
        pyramid.setTranslateX(200);
        pyramid.setTranslateY(100);
        pyramid.setTranslateZ(200);
        root.getChildren().add(pyramid);
        
        RotateTransition rt1 = new RotateTransition();
        rt1.setNode(box);
        rt1.setDuration(Duration.millis(3000));
        rt1.setAxis(Rotate.Z_AXIS);
        rt1.setByAngle(360);
        rt1.setCycleCount(Animation.INDEFINITE);
        rt1.setInterpolator(Interpolator.LINEAR);
        rt1.play();
        
        RotateTransition rt2 = new RotateTransition();
        rt2.setNode(cylinder);
        rt2.setDuration(Duration.millis(3000));
        rt2.setAxis(Rotate.X_AXIS);
        rt2.setByAngle(360);
        rt2.setCycleCount(Animation.INDEFINITE);
        rt2.setInterpolator(Interpolator.LINEAR);
        rt2.play();
        
        RotateTransition rt3 = new RotateTransition();
        rt3.setNode(pyramid);
        rt3.setDuration(Duration.millis(3000));
        rt3.setAxis(Rotate.Y_AXIS);
        rt3.setByAngle(360);
        rt3.setCycleCount(Animation.INDEFINITE);
        rt3.setInterpolator(Interpolator.LINEAR);
        rt3.play();
        
        RotateTransition rt4 = new RotateTransition();
        rt4.setNode(sphere);
        rt4.setDuration(Duration.millis(9000));
        rt4.setAxis(Rotate.Y_AXIS);
        rt4.setByAngle(360);
        rt4.setCycleCount(Animation.INDEFINITE);
        rt4.setInterpolator(Interpolator.LINEAR);
        rt4.play();
        
        PointLight light = new PointLight(Color.LIGHTBLUE);
        light.setTranslateX(-1000);
        light.setTranslateY(100);
        light.setTranslateZ(-1000);
        root.getChildren().add(light);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("3D World");
        primaryStage.show();
        
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
