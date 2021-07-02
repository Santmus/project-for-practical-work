package sample.Animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Класс предназначен для обрабатки анимации, необходимых полей
 * @author Евгений Казаченко
 * @version 1.0.12 - SHAPSHOOT
 * @since  1.0.6
 * */
public class Shake {
    private final TranslateTransition translateTransition;

    /**
     * Конструктор, которой добавляет анимацию необходимому полю
     * @since 1.0.6
     * @param node элемент окна
     * */
    public Shake(Node node){
        translateTransition = new TranslateTransition(Duration.millis(60), node);

        translateTransition.setFromX(-10f);
        translateTransition.setByX(10f);

        translateTransition.setCycleCount(3);
        translateTransition.setAutoReverse(true);

    }

    /**
     * Метод, предназначен для активации анимации
     * @since 1.0.6
     * @see Shake#Shake(Node)
     * */
    public void playAnim(){
        translateTransition.playFromStart();
    }
}
