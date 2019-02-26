package com.mateusrovari.location.play;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.mateusrovari.location.permissions.PermissionActivity;


/**
 * Activity que gerencia a conectividade com o Google Play Services
 */
public abstract class GooglePlayActivity extends PermissionActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    /**
     * Chave para armazenamento do atributo resolvingError no Bundle da activity
     */
    private static final String STATE_RESOLVING_ERROR = "resolving_error";

    /**
     * ID do dialog de erro de conexão
     */
    public static final String DIALOG_ERROR = "dialog_error";

    /**
     * ID de requisição da resolução do erro
     */
    public static final int REQUEST_RESOLVE_ERROR = 1001;

    /**
     * Indica se o erro de conexão já está sendo resolvido
     */
    private boolean resolvingError;

    /**
     * Objeto que dá acesso às APIs do Google Play Services
     */
    private GoogleApiClient apiClient;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // Instancia a API do Google Play Services
        apiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)           // Adiciona a API de localização
                .addConnectionCallbacks(this)           // Registra o listener de conexão bem sucedida
                .addOnConnectionFailedListener(this)    // Registra o listener de falha de conexão
                .build();

        // Define o estado de resolução de erro. Se a activity estiver sendo reconstruída, lê o valor do Bundle
        resolvingError = savedInstanceState != null && savedInstanceState.getBoolean(STATE_RESOLVING_ERROR, false);
	}

    @Override
    protected void onStart() {
        super.onStart();

        // Faz a conexão ao Google Play Services (se um erro já não estiver sendo tratado)
        if (!resolvingError) {
            apiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        // Desconecta do Google Play Services
        apiClient.disconnect();

        super.onStop();
    }

    /**
     * Indica que a conexão foi feita com sucesso
     * @param bundle
     */
    @Override
    public void onConnected(Bundle bundle) {
        // Avisa que a conexão ao Google Play Services foi feita com sucesso
        onGooglePlayServicesConnected();
    }

    /**
     * Indica que a conexão foi suspensa
     * @param i
     */
    @Override
    public void onConnectionSuspended(int i) {
        // Avisa que a conexão ao Google Play Services foi suspensa
        onGooglePlayServicesConnectionSuspended(i);
    }

    /**
     * Indica que a conexão falhou
     * @param connResult Indicativo de erro
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connResult) {
        // Se o erro já estiver sendo resolvido, não faz nada
        if (resolvingError) {
            return;
        }

        //Indica que uma solução está sendo buscada
        resolvingError = true;

        // Verifica se o erro tem solução
        if (connResult.hasResolution()) {
            try {
                // Se tem solução, inicia o processo de resolução
                resolvingError = true;
                connResult.startResolutionForResult(this, REQUEST_RESOLVE_ERROR);

            } catch (IntentSender.SendIntentException e) {
                // Se deu algum problema no envio da intent, tenta conectar novamente
                apiClient.connect();
            }

        } else {
            // Se o erro não tem solução, exibe um dialog falando sobre o erro
            ErrorDialog dialog = new ErrorDialog();
            Bundle args = new Bundle();
            args.putInt(DIALOG_ERROR, connResult.getErrorCode());
            dialog.setArguments(args);
            dialog.show(getFragmentManager(), "errorDialog");

            // Avisa sobre o erro que ocorreu
            onGooglePlayServicesConnectionFailure(connResult.getErrorCode());
        }
    }

    @Override
    /**
     * Chamado quando o processo de resolução de erro termina
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_RESOLVE_ERROR) {
            // Determina que o erro não está mais sendo resolvido
            resolvingError = false;

            // Se deu tudo certo com a resolução, conecta no serviço
            if (resultCode == RESULT_OK) {
                if (!apiClient.isConnecting() && apiClient.isConnected()) {
                    apiClient.connect();
                }
            }
        }
    }

    /**
     * Invocado quando a activity está prestes a ser destruída
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Grava o estado de resolvingError
        outState.putBoolean(STATE_RESOLVING_ERROR, resolvingError);
    }

    /**
     * Método invocado quando o dialog de erro exibido é finalizado
     */
    public void onDialogDismissed() {
        // Indica que uma solução não está mais sendo buscada
        resolvingError = false;
    }

    /**
     * Indica que a conexão foi feita com sucesso
     */
    protected abstract void onGooglePlayServicesConnected();

    /**
     * Indica que a conexão foi suspensa
     * @param errorCode Código do erro
     */
    protected abstract void onGooglePlayServicesConnectionSuspended(int errorCode);

    /**
     * Indica que a conexão falhou
     * @param errorCode Código do erro
     */
    protected abstract void onGooglePlayServicesConnectionFailure(int errorCode);

    /**
     * Classe de dialog de erro
     */
    public static class ErrorDialog extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Lê o código de erro
            int errorCode = this.getArguments().getInt(DIALOG_ERROR);

            // Retorna o dialog de resolução de erro
            return GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), errorCode, REQUEST_RESOLVE_ERROR);
        }

        /**
         * Chamado quando o dialog é fechado
         * @param dialog
         */
        @Override
        public void onDismiss(DialogInterface dialog) {
            // Avisa a activity que o dialog foi fechado
            ((GooglePlayActivity) dialog).onDialogDismissed();
        }
    }

    /**
     * Retorna o objeto de conexão com o Google Play Services
     * @return
     */
    public GoogleApiClient getApiClient() {
        return apiClient;
    }
}
