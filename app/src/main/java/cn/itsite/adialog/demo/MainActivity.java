package cn.itsite.adialog.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author leguang
 * @version v0.0.0
 * @E-mail langmanleguang@qq.com
 * @time 2016/11/24 0024 9:08
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button btDialog;
    private Button btDialogFragment;
    private Button bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        btDialog = findViewById(R.id.bt_dialog);
        btDialogFragment = findViewById(R.id.bt_dialog_fragment);
        bottomSheet = findViewById(R.id.bt_bottom_sheet);
    }

    private void initData() {
        btDialog.setOnClickListener(this);
        btDialogFragment.setOnClickListener(this);
        bottomSheet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_dialog:
                startActivity(new Intent(this, DialogActivity.class));
                break;
            case R.id.bt_dialog_fragment:
                startActivity(new Intent(this, DialogFragmentActivity.class));
                break;
            case R.id.bt_bottom_sheet:
                startActivity(new Intent(this, BottomSheetActivity.class));
                break;
            default:
                break;
        }
    }
}
