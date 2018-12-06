package com.kyhsgeekcode.swfdecompiler;

import android.app.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.codekidlabs.storagechooser.*;
import com.jpexs.decompiler.flash.*;
import java.io.*;

public class MainActivity extends Activity implements View.OnClickListener
{
	private final String TAG="SWFDecompiler";
	
	String input="";
	String output="";
	File outputDir;
	@Override
	public void onClick(View p1)
	{
		switch (p1.getId())
		{
			case R.id.mainButtonInput:
				{
					StorageChooser chooser = new StorageChooser.Builder()
						.withActivity(MainActivity.this)
						.withFragmentManager(getFragmentManager())
						.allowCustomPath(true)
						.setType(StorageChooser.FILE_PICKER)
						//.withMemoryBar(true)
						.build();
					chooser.show();
					chooser.setOnSelectListener(new StorageChooser.OnSelectListener() {
							@Override
							public void onSelect(String path)
							{
								String ext=getExt(path);
								if (!"swf".equalsIgnoreCase(ext))
								{
									Toast.makeText(MainActivity.this, "not swf:" + ext, 3).show();
									return;
								}
								try
								{
									swf = new SWF(new FileInputStream(path));
									etInput.setText(path);
								}
								catch (IOException e)
								{
									ReportError(e, "");
								}
								return;
							}
						});
				}
				break;// Show dialog whenever you want by chooser.show(); // get path that the user has chosen

			case R.id.mainButtonOutput:
				{
					StorageChooser chooser = new StorageChooser.Builder()
						.withActivity(MainActivity.this)
						.withFragmentManager(getFragmentManager())
						.allowCustomPath(true)
						.setType(StorageChooser.DIRECTORY_CHOOSER)
						//.withMemoryBar(true)
						.build();
					chooser.show();
					chooser.setOnSelectListener(new StorageChooser.OnSelectListener() {
							@Override
							public void onSelect(String path)
							{	
								outputDir=new File(path);
								if(!outputDir.isDirectory())
								{
									ReportError(new Exception(),"Not a directory!"+path);
									return;
								}
								output=path;
								etOutput.setText(path);
								return;
							}
						});}

				break;
			default:
				break;
		}
		return ;
	}

	Button btInput,btOutput;
	EditText etInput,etOutput;
	SWF swf;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		btInput = (Button) findViewById(R.id.mainButtonInput);
		btOutput = (Button) findViewById(R.id.mainButtonOutput);
		etInput = (EditText) findViewById(R.id.mainEditTextInput);
		etOutput = (EditText) findViewById(R.id.mainEditTextOutput);
		btInput.setOnClickListener(this);
		btOutput.setOnClickListener(this);
		return;
	}
	public void ReportError(Exception e, String msg)
	{
		Log.e(TAG, msg, e);
		Toast.makeText(this, msg, 4).show();
	}

	String getExt(String s)
	{
		int i=s.lastIndexOf(".");
		if (i < 0)
			return s;
		return s.substring(i);
	}
}
