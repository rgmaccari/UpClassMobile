package utils;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class DataPickerUtil extends DialogFragment implements DataPickerDialog.OnDateSetListener {
    private DataPickerList listener;

    public interface DataPickerListener{
        void onDateSelected(int year, int month, int day);
    }

    public void setListener(DataPickerListener listener){
        this.listener = listener;
    }
}
