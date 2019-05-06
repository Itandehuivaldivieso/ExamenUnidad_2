package sample;

import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/*public class ControlaTiempo extends Thread {
    private TextField segundos, minutos;
    public ControlaTiempo(TextField minutos, TextField segundos) {
        super();
        this.segundos = segundos;
        this.minutos  = minutos;
    }

    public void run() {
        int nuMin = 0;
        int nuSeg = 0;
        try {
            for (; ; ) {
                if (nuSeg != 59)
                    nuSeg++;
                else {
                    if (nuMin != 59) {
                        nuSeg = 0;
                        nuMin++;
                    }
                }
                segundos.setText(Integer.toString(nuSeg));
                minutos.setText(Integer.toString(nuMin));
                System.out.println( nuMin + ":" + nuSeg);
                sleep(999);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    public void stoped(){

    }
}*/
public class ControlaTiempo extends Task<String> {

    private TextField segundos, minutos;
    public ControlaTiempo(TextField minutos, TextField segundos) {
        super();
        this.segundos = segundos;
        this.minutos  = minutos;
    }

    @Override
    protected String call() throws Exception {
        int nuMin = 0;
        int nuSeg = 0;
        try {
            for (; ; ) {
                if (nuSeg != 59)
                    nuSeg++;
                else {
                    if (nuMin != 59) {
                        nuSeg = 0;
                        nuMin++;
                    }
                }
                segundos.setText(Integer.toString(nuSeg));
                minutos.setText(Integer.toString(nuMin));
                System.out.println( nuMin + ":" + nuSeg);

                Thread.sleep(999);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return "Tarea terminada";
    }

    @Override
    public void succeeded()
    {
        super.succeeded();
        updateMessage("La tarea terminó exitosamente.");
    }
}
