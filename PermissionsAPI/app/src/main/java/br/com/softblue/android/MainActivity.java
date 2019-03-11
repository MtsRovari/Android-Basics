package br.com.softblue.android;

import android.Manifest;
import android.app.Activity;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.List;

import br.com.softblue.android.permission.PermissionActivity;
import br.com.softblue.android.permission.PermissionDialog;

public class MainActivity extends PermissionActivity implements PermissionDialog.OnPermissionDialogListener {

    private static final int ID = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enablePermissions(ID, true, Manifest.permission.CAMERA);
    }

    @Override
    protected void onPermissionsNeeded(int requestPermissionId, List<String> permissions) {
        showPermissionDialog(0, "Esta permissão é importante. Vamos autorizar?");
    }

    @Override
    protected void onPermissionsDenied(int requestPermissionId, List<String> permissions) {
        Toast.makeText(this, "Permissão negada, não podemos continuar...", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPermissionsGranted(int requestPermissionId, List<String> permissions) throws SecurityException {
        if (requestPermissionId == ID && permissions.contains(Manifest.permission.CAMERA)) {
            CameraManager cm = (CameraManager) getSystemService(CAMERA_SERVICE);
            String[] ids = new String[0];
            try {
                ids = cm.getCameraIdList();

                cm.openCamera(ids[0], new CameraDevice.StateCallback() {
                    @Override
                    public void onOpened(@NonNull CameraDevice cameraDevice) {
                        Toast.makeText(MainActivity.this, "Câmera aberta", Toast.LENGTH_LONG).show();
                        cameraDevice.close();
                    }

                    @Override
                    public void onDisconnected(@NonNull CameraDevice cameraDevice) {
                    }

                    @Override
                    public void onError(@NonNull CameraDevice cameraDevice, int i) {

                    }
                }, null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPermissionDialogResult(int dialogId, boolean accepted) {
        if (accepted) {
            enablePermissions(ID, false, Manifest.permission.CAMERA);
        }
    }
}
