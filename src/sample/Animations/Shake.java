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
     * Метож который будет анимировать необходимый поле окна
     * @since 1.0.6
     * @param node элемент окна
     * */
    public void playAnimationField(Node node){
        translateTransition = new TranslateTransition(Duration.millis(70), node);

        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);

        translateTransition.setCycleCount(3);
        translateTransition.setAutoReverse(true);
        playAnim();
    }

    /**
     * Метод который проигрывает анимацию
     * @since 1.0.6
     * @see Shake#playAnimationField(Node)
     * */
    private void playAnim(){
        translateTransition.playFromStart();
    }
}
