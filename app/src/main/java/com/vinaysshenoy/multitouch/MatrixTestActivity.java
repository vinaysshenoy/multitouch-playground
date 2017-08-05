package com.vinaysshenoy.multitouch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vinaysshenoy.multitouch.widget.matrixtest.MatrixTestView;

/**
 * Created by vinaysshenoy on 05/08/17.
 */

public class MatrixTestActivity extends AppCompatActivity {

    private MatrixTestView matrixTestView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_test);
        matrixTestView = (MatrixTestView) findViewById(R.id.matrixtest);
    }
}
