package com.gadroves.gsisinve.UI;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by geykel on 11/03/2015.
 */
public class ResizeListener implements EventHandler<MouseEvent> {
    double dx;
    double dy;
    double initX;
    double initY;
    double deltaX;
    double deltaY;
    double border = 10;
    double minH;
    double minW;
    boolean moveH;
    boolean moveV;
    boolean resizeH = false;
    boolean resizeV = false;
    Scene scene;
    Stage stage;

    public ResizeListener(Stage stage, double minH, double minW) {
        this.scene = stage.getScene();
        this.scene.setOnMouseMoved(this);
        this.scene.setOnMousePressed(this);
        this.scene.setOnMouseDragged(this);
        this.minH = minH;
        this.minW = minW;
        this.stage = stage;
    }

    public Stage getStage() {
        return this.stage;
    }

    public Scene getScene() {
        return this.scene;
    }

    @Override
    public void handle(MouseEvent t) {
        if (MouseEvent.MOUSE_MOVED.equals(t.getEventType())) {
            if (t.getX() < border && t.getY() < border) {
                scene.setCursor(Cursor.NW_RESIZE);
                resizeH = true;
                resizeV = true;
                moveH = true;
                moveV = true;
            } else if (t.getX() < border && t.getY() > scene.getHeight() - border) {
                scene.setCursor(Cursor.SW_RESIZE);
                resizeH = true;
                resizeV = true;
                moveH = true;
                moveV = false;
            } else if (t.getX() > scene.getWidth() - border && t.getY() < border) {
                scene.setCursor(Cursor.NE_RESIZE);
                resizeH = true;
                resizeV = true;
                moveH = false;
                moveV = true;
            } else if (t.getX() > scene.getWidth() - border && t.getY() > scene.getHeight() - border) {
                scene.setCursor(Cursor.SE_RESIZE);
                resizeH = true;
                resizeV = true;
                moveH = false;
                moveV = false;
            } else if (t.getX() < border || t.getX() > scene.getWidth() - border) {
                scene.setCursor(Cursor.E_RESIZE);
                resizeH = true;
                resizeV = false;
                moveH = (t.getX() < border);
                moveV = false;
            } else if (t.getY() < border || t.getY() > scene.getHeight() - border) {
                scene.setCursor(Cursor.N_RESIZE);
                resizeH = false;
                resizeV = true;
                moveH = false;
                moveV = (t.getY() < border);
            } else {
                scene.setCursor(Cursor.DEFAULT);
                resizeH = false;
                resizeV = false;
                moveH = false;
                moveV = false;
            }
        } else if (MouseEvent.MOUSE_PRESSED.equals(t.getEventType())) {
            dx = stage.getWidth() - t.getX();
            dy = stage.getHeight() - t.getY();
            initX = t.getScreenX() - stage.getX();
            initY = t.getScreenY() - stage.getY();
        } else if (MouseEvent.MOUSE_DRAGGED.equals(t.getEventType())) {
            if (resizeH && !stage.isMaximized()) {
                if (stage.getWidth() <= this.minW/*minSize.width*/) {
                    if (moveH) {
                        deltaX = stage.getX() - t.getScreenX();
                        if (t.getX() < 0) {// if new > old, it's permitted
                            if (deltaX + stage.getWidth() >= this.minW) {
                                stage.setWidth(deltaX + stage.getWidth());
                                stage.setX(t.getScreenX());
                            }
                        }
                    } else {
                        if (t.getX() + dx - stage.getWidth() > 0) {
                            if (t.getX() + dx >= this.minW)
                                stage.setWidth(t.getX() + dx);
                        }
                    }
                } else if (stage.getWidth() > this.minW/*minSize.width*/) {
                    if (moveH) {
                        deltaX = stage.getX() - t.getScreenX();
                        if (deltaX + stage.getWidth() >= this.minW) {
                            stage.setWidth(deltaX + stage.getWidth());
                            stage.setX(t.getScreenX());
                        }
                    } else {
                        if (t.getX() + dx >= this.minW)
                            stage.setWidth(t.getX() + dx);
                    }
                }
            } else if (resizeV && !stage.isMaximized()) {
                if (stage.getHeight() <= this.minH/*minSize.width*/) {
                    if (moveV) {
                        deltaY = stage.getY() - t.getScreenY();
                        if (t.getY() < 0) {// if new > old, it's permitted
                            if (deltaY + stage.getHeight() >= this.minH) {
                                stage.setHeight(deltaY + stage.getHeight());
                                stage.setY(t.getScreenY());
                            }
                        }
                    } else {
                        if (t.getY() + dy - stage.getHeight() > 0) {
                            if (t.getY() + dy >= this.minH)
                                stage.setHeight(t.getY() + dy);
                        }
                    }
                } else if (stage.getHeight() > this.minH/*minSize.width*/) {
                    if (moveV) {
                        deltaY = stage.getY() - t.getScreenY();
                        if (deltaY + stage.getHeight() >= this.minH) {
                            stage.setHeight(deltaY + stage.getHeight());
                            stage.setY(t.getScreenY());
                        }
                    } else {
                        if (t.getY() + dy >= this.minH)
                            stage.setHeight(t.getY() + dy);
                    }
                }
            } else {
                if (stage.isMaximized()) {
                    stage.setMaximized(false);
                } else {
                    stage.setX(t.getScreenX() - initX);
                    stage.setY(t.getScreenY() - initY);
                    scene.setCursor(Cursor.MOVE);
                }
            }
        }
    }
}
