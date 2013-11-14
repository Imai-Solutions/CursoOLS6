package com.imaisolutions.opengl.samples;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.imaisolutions.opengl.tools.GameActivity;
import com.imaisolutions.opengl.tools.GameListener;
import com.imaisolutions.opengl.tools.Mesh;
import com.imaisolutions.opengl.tools.Texture;
import com.imaisolutions.opengl.tools.Mesh.PrimitiveType;
import com.imaisolutions.opengl.tools.Texture.TextureFilter;
import com.imaisolutions.opengl.tools.Texture.TextureWrap;

public class BackgroundSample extends GameActivity implements GameListener
{
	Mesh mesh;
	Texture texture;
	
	public void onCreate( Bundle bundle )
	{
		super.onCreate( bundle );
		setGameListener(this);
	}
	
	@Override
	public void setup(GameActivity activity, GL10 gl) 
	{	
		mesh = new Mesh( gl, 4, false, true, false );
		mesh.texCoord( 0, 1 );		
		mesh.vertex( -1f, -1f, 0 );
		mesh.texCoord( 1, 1 );		
		mesh.vertex( 1f, -1f, 0 );
		mesh.texCoord( 1, 0 );		
		mesh.vertex( 1f, 1f, 0 );
		mesh.texCoord( 0, 0 );		
		mesh.vertex( -1f, 1f, 0 );
		
		try
		{
			Bitmap bitmap = BitmapFactory.decodeStream( getAssets().open( "planet.jpg" ) );
			texture = new Texture( gl, bitmap, TextureFilter.Linear, TextureFilter.Linear, TextureWrap.ClampToEdge, TextureWrap.ClampToEdge );
		}
		catch( Exception ex )
		{
			Log.d("Sample", "oh noes!");
			System.exit(-1);
		}
	}
	
	@Override
	public void mainLoopIteration(GameActivity activity, GL10 gl) 
	{			
		gl.glViewport( 0, 0, activity.getViewportWidth(), activity.getViewportHeight() );
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glEnable( GL10.GL_TEXTURE_2D );
		
		texture.bind();
		mesh.render(PrimitiveType.TriangleFan);
	}
}
