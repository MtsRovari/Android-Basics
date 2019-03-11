package br.com.softblue.android.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Dialog que exibe um motivo de solicitação da permissão
 */
public class PermissionDialog extends DialogFragment implements DialogInterface.OnClickListener {
    /**
     * Propriedade da mensagem do dialog
     */
    public static final String ARG_MESSAGE = "dialog_message";

    /**
     * Propriedade do ID do dialog
     */
    public static final String ARG_ID = "dialog_id";

    /**
     * Listener para ser notificado sobre a resposta escolhida no dialog
     */
    private OnPermissionDialogListener listener;

    /**
     * ID do dialog
     */
    private int dialogId;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity activity = getActivity();

        // A activity atrelada ao dialog deve implementar OnPermissionDialogListener
        if (!(activity instanceof OnPermissionDialogListener)) {
            throw new IllegalArgumentException("A activity deve implementar PermissionDialog.OnPermissionDialogListener");
        }

        // Atribui o listener (activity)
        listener = (OnPermissionDialogListener) activity;

        // Lê os argumentos do dialog
        String message = getArguments().getString(ARG_MESSAGE);
        dialogId = getArguments().getInt(ARG_ID);

        // Cria o dialog
        return new AlertDialog.Builder(activity)
                .setTitle("Permissão necessária")
                .setMessage(message)
                .setPositiveButton("Sim", this)
                .setNegativeButton("Não", this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        // Descobre se o usuário escolheu o 'Sim' (true) ou 'Não' (false)
        boolean accepted = which == DialogInterface.BUTTON_POSITIVE;

        // Chama o listener
        listener.onPermissionDialogResult(dialogId, accepted);
    }

    /**
     * Interface para integração da activity com o dialog
     */
    public interface OnPermissionDialogListener {
        /**
         * Método chamado quando o usuário escolheu uma resposta no dialog ('Sim' ou 'Não')
         * @param dialogId ID do dialog
         * @param accepted true se a escolha foi 'Sim'; false se a escolha foi 'Não'
         */
        void onPermissionDialogResult(int dialogId, boolean accepted);
    }
}
