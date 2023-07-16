package com.suad.venttome;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.card.MaterialCardView;
import com.suad.venttome.adapters.TextListAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TextListFragment extends Fragment implements TextListAdapter.onTextItemListClick {

    private ConstraintLayout textPlayerSheet;
    private BottomSheetBehavior textBottomSheetBehavior;
    private File[] allTextFiles;

    private TextListAdapter textListAdapter;
    //UI elements
    private RecyclerView textList;

    private TextView textPlayerHeader;
    private TextView textPlayerFilename;
    private TextView readText;
    private String txtFile;
    private File fileToRead;
    private boolean isReading = false;
    private TextView playerReadText;
    private File fileToDelete;
    private Intent deleteThis;
    private ArrayList<File[]> allTextFilesArray;
    private ImageButton deleteText;
    private MaterialCardView theCard;
    private ImageButton shareText;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static TextListFragment getInstance() {
        TextListFragment fragment = new TextListFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_text_list, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        textPlayerSheet = view.findViewById(R.id.text_player_sheet);
        textBottomSheetBehavior = BottomSheetBehavior.from(textPlayerSheet);
        textList = view.findViewById(R.id.text_list_view);
        playerReadText = view.findViewById(R.id.player_read_text);

        playerReadText.setMovementMethod(new ScrollingMovementMethod());
        readText = view.findViewById(R.id.player_read_text);
        deleteText = view.findViewById(R.id.delete_text_entry);
        textPlayerFilename = view.findViewById(R.id.text_player_filename);
        theCard = view.findViewById(R.id.textCard);
        shareText = view.findViewById(R.id.share_text_entry);


        //Reversing textList
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        textList.setLayoutManager(linearLayoutManager);


        String path = getActivity().getFilesDir().getAbsolutePath()+"/TextEntries/";
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        allTextFiles = directory.listFiles();

        textListAdapter = new TextListAdapter(allTextFiles, this);

        textList.setHasFixedSize(true);
        textList.setAdapter(textListAdapter);
        allTextFilesArray = new ArrayList<>();
        allTextFilesArray.add(allTextFiles);


        textBottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    textBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //Leave this EMPTY
            }
        });


    }


    @Override
    public void onClickListener(File file, int position) {
        fileToRead = file;
        if(fileToRead.getName().equals("rList")){
            fileToRead.delete();
        }
        if (isReading = true){
            loadText(fileToRead);
        } else {
            textBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }


    }


    private void loadText(final File fileToRead) {


        isReading = true;
        textPlayerFilename.setText(fileToRead.getName());
        textBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        deleteText.setVisibility(View.VISIBLE);
        shareText.setVisibility(View.VISIBLE);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileToRead);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            readText.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        deleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TextListFragment.this.getContext(), "This text diary has been deleted", Toast.LENGTH_LONG).show();
                textBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                fileToRead.delete();
            }
        });

        shareText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    String shareMessage= "Diary entry: \n"+ readText.getText().toString() + "\n";
                    shareMessage = shareMessage + "Download Here: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID;
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch(Exception e) {
                    e.toString();
                }


            }
        });

    }


}
