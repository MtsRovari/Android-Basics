package com.mateusrovari.squarecamera;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 */
public class EditSavePhotoFragment extends Fragment {

    public static final String TAG = EditSavePhotoFragment.class.getSimpleName();
    public static final String BITMAP_KEY = "bitmap_byte_array";
    public static final String ROTATION_KEY = "rotation";
    public static final String IMAGE_INFO = "image_info";

    private static final int REQUEST_STORAGE = 1;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private ImageButton btnCordinates;
    
    public static Fragment newInstance(byte[] bitmapByteArray, int rotation,
                                       @NonNull ImageParameters parameters) {
        Fragment fragment = new EditSavePhotoFragment();

        Bundle args = new Bundle();
        args.putByteArray(BITMAP_KEY, bitmapByteArray);
        args.putInt(ROTATION_KEY, rotation);
        args.putParcelable(IMAGE_INFO, parameters);

        fragment.setArguments(args);
        return fragment;
    }

    public EditSavePhotoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.squarecamera__fragment_edit_save_photo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = view.findViewById(R.id.textView);
        textView2 = view.findViewById(R.id.textView2);
        textView3 = view.findViewById(R.id.textView3);
        textView4 = view.findViewById(R.id.textView4);
        textView5 = view.findViewById(R.id.textView5);
        textView6 = view.findViewById(R.id.textView6);
        textView7 = view.findViewById(R.id.textView7);
        textView8 = view.findViewById(R.id.textView8);
        textView9 = view.findViewById(R.id.textView9);
        textView10 = view.findViewById(R.id.textView10);
        btnCordinates = view.findViewById(R.id.button);

        int rotation = getArguments().getInt(ROTATION_KEY);
        byte[] data = getArguments().getByteArray(BITMAP_KEY);
        ImageParameters imageParameters = getArguments().getParcelable(IMAGE_INFO);

        if (imageParameters == null) {
            return;
        }

        final ImageView photoImageView = (ImageView) view.findViewById(R.id.photo);

        imageParameters.mIsPortrait =
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        final View topView = view.findViewById(R.id.topView);
        if (imageParameters.mIsPortrait) {
            topView.getLayoutParams().height = imageParameters.mCoverHeight;
        } else {
            topView.getLayoutParams().width = imageParameters.mCoverWidth;
        }

        rotatePicture(rotation, data, photoImageView);

        view.findViewById(R.id.save_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePicture();
            }
        });
    }

    private void rotatePicture(int rotation, byte[] data, ImageView photoImageView) {
        Bitmap bitmap = ImageUtility.decodeSampledBitmapFromByte(getActivity(), data);
//        Log.d(TAG, "original bitmap width " + bitmap.getWidth() + " height " + bitmap.getHeight());
//        Log.d(TAG, "rotatePicture: bitmap before " + bitmap);
        if (rotation != 0) {
            Bitmap oldBitmap = bitmap;

            Matrix matrix = new Matrix();
            matrix.postRotate(rotation);

            bitmap = Bitmap.createBitmap(
                    oldBitmap, 0, 0, oldBitmap.getWidth(), oldBitmap.getHeight(), matrix, false
            );

            oldBitmap.recycle();
        }
//        Log.d(TAG, "original after " + bitmap.getWidth() + " height " + bitmap.getHeight());

        photoImageView.setImageBitmap(bitmap);
//        Log.d(TAG, "rotatePicture: bitmap after " + bitmap);

//        String[] range = {
//                "245c73",
//                "245162",
//                "215a6e",
//                "0e4a62",
//                "4e7b90",
//                "28586e",
//                "5f8999",
//                "0b415b",
//                "18516c",
//                "0b3d54",
//        };
//
//        ArrayList cy = new ArrayList<Integer>();
//        for (int i = 0; i < 10; i++) {
//            cy.add(400+(i*25));
//        }
//
//        ArrayList cx = new ArrayList<Integer>();
//        for (int i = 0; i < 10; i++) {
//            cx.add(50+(i*5));
//        }
//
//        int matches = 0;
//        for (int j = 0; j < 10; j++) {
//            int color = bitmap.getPixel((Integer) cx.get(j),(Integer) cy.get(j));
//            int r = Color.red(color);
//            int g = Color.green(color);
//            int b = Color.blue(color);
//
//            int tolerance = 15;
//
//
//            for (int i = 0; i < range.length; i++) {
//
//                int color1 = Color.parseColor("#" + range[i]);
//                int rangeR = Color.red(color1);
//                int rangeG = Color.green(color1);
//                int rangeB = Color.blue(color1);
//
//
//                int minR = Math.max(rangeR - tolerance, 0);
//                int minG = Math.max(rangeG - tolerance, 0);
//                int minB = Math.max(rangeB - tolerance, 0);
//                int maxR = Math.min(rangeR + tolerance, 255);
//                int maxG = Math.min(rangeG + tolerance, 255);
//                int maxB = Math.min(rangeB + tolerance, 255);
//
//                if ((r > minR && r < maxR) && (g > minG && g < maxG) && (b > minB && b < maxB)) {
//                    matches++;
//                    Log.d(TAG, "rotatePicture: certo " + range[i]);
//                    break;
//                }
//
////                Log.d(TAG, "rotatePicture colors: " + Integer.toHexString(Color.rgb(r, g, b)));
////                Log.d(TAG, "rotatePicture MIN " + Integer.toHexString(Color.rgb(minR, minG, minB)));
////                Log.d(TAG, "rotatePicture MAX " + Integer.toHexString(Color.rgb(maxR, maxG, maxB)));
//            }
//        }
//        Log.d(TAG, "rotatePicture: matches " + matches);

    }


    private void savePicture() {
        requestForPermission();
    }

    private void requestForPermission() {
        RuntimePermissionActivity.startActivity(EditSavePhotoFragment.this,
                REQUEST_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK != resultCode) return;

        if (REQUEST_STORAGE == requestCode && data != null) {
            final boolean isGranted = data.getBooleanExtra(RuntimePermissionActivity.REQUESTED_PERMISSION, false);
            final View view = getView();
            if (isGranted && view != null) {
                ImageView photoImageView = view.findViewById(R.id.photo);

                Bitmap bitmap = ((BitmapDrawable) photoImageView.getDrawable()).getBitmap();
                Uri photoUri = ImageUtility.savePicture(getActivity(), bitmap);

                ((CameraActivity) getActivity()).returnPhotoUri(photoUri);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
