package com.kyhsgeekcode.swfdecompiler;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.jpexs.decompiler.flash.*;

public class MainActivity extends Activity implements View.OnClickListener
{
	@Override
	public void onClick(View p1)
	{
		switch(p1.getId())
		{
			case R.id.mainButtonInput:
				
			case R.id.mainButtonOutput:
				
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
		btInput=(Button) findViewById(R.id.mainButtonInput);
		btOutput=(Button) findViewById(R.id.mainButtonOutput);
		etInput=(EditText) findViewById(R.id.mainEditTextInput);
		etOutput=(EditText) findViewById(R.id.mainEditTextOutput);
		btInput.setOnClickListener(this);
		btOutput.setOnClickListener(this);
		return;
	}
}
