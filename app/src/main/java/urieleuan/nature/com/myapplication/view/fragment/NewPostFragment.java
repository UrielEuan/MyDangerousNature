package urieleuan.nature.com.myapplication.view.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import urieleuan.nature.com.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewPostFragment extends Fragment {

    ImageView ivPicture;
    Button btnTakePicture;
    String mCurrentPhotoPath;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public NewPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_new_post2, container, false);

        ivPicture = (ImageView) view.findViewById(R.id.ivPicture);
        btnTakePicture = (Button) view.findViewById(R.id.btnTakePicture);

        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        return view;
    }

    private void takePicture() {

        //ASIENDO USO DE LA CAMARA
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null){

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (photoFile != null){

                Uri photoURI = FileProvider.getUriForFile(getActivity(),
                        "cbtis72.regina.platzigram",
                        photoFile);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        //PARA HACER USO DE LA IMAGEN
        if (requestCode ==REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK){

            Picasso.with(getActivity()).load(mCurrentPhotoPath).into(ivPicture);
            addPictureToGallery();
            Toast.makeText(getActivity(), mCurrentPhotoPath, Toast.LENGTH_LONG).show();

            /** Bundle extras = data.getExtras();
             Bitmap imageBitmap = (Bitmap) extras.get("data");
             ivPicture.setImageBitmap(imageBitmap);  */
        }
    }

    private File createImageFile() throws IOException{

        //Imagen con buena calidad

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //nombre de la imagen
        String imageFileName = "JPEG_" + timeStamp + "_";
        //directorio
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName, ".jpg", storageDir );

        //Guardar la foto
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();

        return image;
    }


    private void addPictureToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File newFile = new File(mCurrentPhotoPath);

        Uri contentUri = Uri.fromFile(newFile);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

}
