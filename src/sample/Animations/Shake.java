package sample.Animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Класс который будет обрабатывать необходимые анимации, для визуализации приложения
 * @author Евгений Казаченко
 * @version 1.0.6 - SHAPSHOOT
 * @since  1.0.6
 * */
public class Shake {
    private TranslateTransition translateTransition;

    /**
     * Конструктор, который будет анимировать необходимое поле
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
     * Метод, который проигрывает анимацию
     * @since 1.0.6
     * @see Shake##Shake(Node)
     * */
    public void playAnim(){
        translateTransition.playFromStart();
    }
}
