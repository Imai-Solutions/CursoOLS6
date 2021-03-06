package com.imaisolutions.opengl.samples;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLU;
import android.os.Bundle;

import com.imaisolutions.opengl.tools.GameActivity;
import com.imaisolutions.opengl.tools.GameListener;
import com.imaisolutions.opengl.tools.Texture;
import com.imaisolutions.opengl.tools.Texture.TextureFilter;
import com.imaisolutions.opengl.tools.Texture.TextureWrap;
import com.imaisolutions.opengl.tools.md2.MD2Instance;
import com.imaisolutions.opengl.tools.md2.MD2Loader;
import com.imaisolutions.opengl.tools.md2.MD2Mesh;

import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

public class MD2Sample extends GameActivity implements GameListener
{
	Texture texture;
	MD2Mesh mesh;
	MD2Instance instance;
	
	public void onCreate( Bundle savedInstance )
	{
		super.onCreate( savedInstance );
		setGameListener( this );
	}
	
	@Override
	public void setup(GameActivity activity, GL10 gl)
	{	
		try
		{
			InputStream in = activity.getAssets().open( "knight.md2" );
			mesh = MD2Loader.load( gl, in );
			instance = new MD2Instance( mesh, 0.2f );
			in.close();
			
			in = getAssets().open( "knight.jpg" );
			Bitmap bitmap = BitmapFactory.decodeStream( in );
			texture = new Texture( gl, bitmap, TextureFilter.Linear, TextureFilter.Linear, TextureWrap.ClampToEdge, TextureWrap.ClampToEdge );
			in.close();
			bitmap.recycle();
		}
		catch( Exception ex )
		{
			ex.printStackTrace();
		}
	}
	
	@Override
	public void mainLoopIteration(GameActivity activity, GL10 gl) {
		gl.glClear( GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT );
		gl.glViewport( 0, 0, activity.getViewportWidth(), activity.getViewportHeight() );
		
		gl.glEnable( GL10.GL_DEPTH_TEST );
		
		gl.glMatrixMode( GL10.GL_PROJECTION );
		gl.glLoadIdentity();
		float aspectRatio = (float)activity.getViewportWidth() / activity.getViewportHeight();
		GLU.gluPerspective( gl, 67, aspectRatio, 1, 100 );

		gl.glMatrixMode( GL10.GL_MODELVIEW );
		gl.glLoadIdentity();
		GLU.gluLookAt( gl, 1, 2, 2, 0, 0, 0, 0, 1, 0 );

		gl.glEnable( GL10.GL_TEXTURE_2D );
        texture.bind();
		instance.render( activity.getDeltaTime() );

	}
}
