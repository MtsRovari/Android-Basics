package com.mateusrovari.location.permissions;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Activity que gerencia runtime permissions
 */
public abstract class PermissionActivity extends Activity {

    /**
     * Faz o processamento de uma ou mais requisições, concedendo-as ou negando-as
     * @param requestPermissionId ID para rastrear a chamada quando a permissão é concedida ou negada
     * @param processRationale Indica se o aviso ao usuário será dado ou será ignorado
     * @param permissions Permissões a serem requisitas. São constantes em android.Manifest.permissions.
     */
    protected void enablePermissions(int requestPermissionId, boolean processRationale, String... permissions) {
        if (permissions == null || permissions.length == 0) {
            // Não existem permissões para processar
            return;
        }

        // Lista de permissões já concedidas
        List<String> permissionsListAlreadyGranted = new ArrayList<>();

        // Lista de permissões a serem concedidas
        List<String> permissionsListToGrant = new ArrayList<>();

        // Lista de permissões onde uma mensagem de motivo deve aparecer ao usuário
        List<String> permissionsListToShowRationale = new ArrayList<>();

        for (String permission : permissions) {
            // Verifica se a permissão já foi concedida
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                // Verifica se é preciso exibir a mensagem de motivo
                if (processRationale && shouldShowRequestPermissionRationale(permission)) {
                    permissionsListToShowRationale.add(permission);
                } else {
                    permissionsListToGrant.add(permission);
                }
            } else {
                permissionsListAlreadyGranted.add(permission);
            }
        }

        // Avisa a activity através das chamadas onPermissionsXXX() com base nos itens das listas

        if (permissionsListToShowRationale.size() > 0) {
            onPermissionsNeeded(requestPermissionId, Collections.unmodifiableList(permissionsListToShowRationale));
        }

        if (permissionsListAlreadyGranted.size() > 0) {
            onPermissionsGranted(requestPermissionId, Collections.unmodifiableList(permissionsListAlreadyGranted));
        }

        // Faz a requisição das permissões. O resultado é coletado no método
        if (permissionsListToGrant.size() > 0) {
            requestPermissions(permissionsListToGrant.toArray(new String[permissionsListToGrant.size()]), requestPermissionId);
        }
    }

    /**
     * Método chamado quando as permissões são concedidas ou negadas
     * @param requestPermissionId ID para rastrear a chamada quando a permissão é concedida ou negada
     * @param permissions Permissões analisadas
     * @param grantResults Resultados das permissões
     */
    @Override
    public void onRequestPermissionsResult(int requestPermissionId, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // Listas para armazenar permissões concedidas e negadas
        List<String> grantedPermissions = new ArrayList<>();
        List<String> failedPermissions = new ArrayList<>();

        for (int i = 0; i < permissions.length; i++) {
            // Coloca o resultado de cada permissão na lista correta
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                grantedPermissions.add(permissions[i]);
            } else {
                failedPermissions.add(permissions[i]);
            }
        }

        // Avisa sobre o resultado do processamento das permissões

        if (grantedPermissions.size() > 0) {
            onPermissionsGranted(requestPermissionId, Collections.unmodifiableList(grantedPermissions));
        }

        if (failedPermissions.size() > 0) {
            onPermissionsDenied(requestPermissionId, Collections.unmodifiableList(failedPermissions));
        }
    }

    /**
     * Exibe um dialog com uma mensagem sobre o motivo da solicitação da permissão
     * @param dialogId ID do dialog para rastreio futuro
     * @param message Mensagem a ser exibida
     */
    protected void showPermissionDialog(int dialogId, String message) {
        // Define os parâmetros do dialog
        Bundle bundle = new Bundle();
        bundle.putInt(PermissionDialog.ARG_ID, dialogId);
        bundle.putString(PermissionDialog.ARG_MESSAGE, message);

        // Cria o objeto do dialog e atribui os parâmetros
        PermissionDialog dialog = new PermissionDialog();
        dialog.setArguments(bundle);

        // Exibe o dialog
        dialog.show(getFragmentManager(), "errorDialog");
    }

    /**
     * Método de notificação de permissões que exibem uma mensagem com o motivo
     * @param requestPermissionId ID para rastrear a chamada
     * @param permissions Permissões processadas
     */
    protected abstract void onPermissionsNeeded(int requestPermissionId, List<String> permissions);

    /**
     * Método de notificação de permissões negadas
     * @param requestPermissionId ID para rastrear a chamada
     * @param permissions Permissões processadas
     */
    protected abstract void onPermissionsDenied(int requestPermissionId, List<String> permissions);

    /**
     * Método de notificação de permissões concedidas
     * @param requestPermissionId ID para rastrear a chamada
     * @param permissions Permissões processadas
     * @throws SecurityException Exceção de acesso
     */
    protected abstract void onPermissionsGranted(int requestPermissionId, List<String> permissions) throws SecurityException;
}
