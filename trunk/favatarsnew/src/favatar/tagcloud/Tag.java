package favatar.tagcloud;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Tag {
	// Our vertices.
	private float vertices[] = {
		      -1.0f,  0.5f, 0.0f,  // 0, Top Left
		      -1.0f, -0.5f, 0.0f,  // 1, Bottom Left
		       1.0f, -0.5f, 0.0f,  // 2, Bottom Right
		       1.0f,  0.5f, 0.0f,  // 3, Top Right
		};
	
	// The order we like to connect them.
	private short[] indices = { 0, 1, 2, 0, 2, 3 };

	float textureCoordinates[] = {
		      -1.0f, -1.0f,   // 0, Bottom Left
		      -1.0f,  1.0f,   // 1, Top Left
		       1.0f,  1.0f,  // 2, Top Right
		       1.0f, -1.0f  // 3, Bottom Right
		};
	
	// Our vertex buffer.
	private FloatBuffer vertexBuffer;

	// Our index buffer.
	private ShortBuffer indexBuffer;
	

	// Our UV texture buffer.
	private FloatBuffer textureBuffer; // New variable.
	
	public Tag() {
		setVertices(vertices);
		setIndices(indices);
		setTextureCoordinates(textureCoordinates);		
	}
	
	/**
	 * This function draws our square on screen.
	 * @param gl
	 */
	public void draw(GL10 gl, int texture) {
		// Counter-clockwise winding.
		gl.glFrontFace(GL10.GL_CCW);
		// Enable face culling.
		gl.glEnable(GL10.GL_CULL_FACE);
		// What faces to remove with the face culling.
		gl.glCullFace(GL10.GL_BACK);
		
		// Enabled the vertices buffer for writing and to be used during 
		// rendering.
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		// Specifies the location and data format of an array of vertex
		// coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

		gl.glEnable(GL10.GL_TEXTURE_2D);				
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		// Point to our buffers
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);	
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture); 
		
		
		
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
		
		

		// Set flat color
		//gl.glColor4f(1.0f, 0.0f, 1.0f, 1.0f);
		
		

		//gl.glDisable(GL10.GL_TEXTURE_2D);
		
		// Disable the vertices buffer.
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		// Disable face culling.
		gl.glDisable(GL10.GL_CULL_FACE);
	}

	public void setVertices(float[] verticesArr)
	{
		vertices = verticesArr;
		// a float is 4 bytes, therefore we multiply the number if 
		// vertices with 4.
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}
	
	public void setIndices(short[] indicesArr)
	{
		indices = indicesArr;
		// short is 2 bytes, therefore we multiply the number if 
		// vertices with 2.
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer = ibb.asShortBuffer();
		indexBuffer.put(indices);
		indexBuffer.position(0);
	}
	
	public void setTextureCoordinates(float[] coordinates)
	{
		textureCoordinates = coordinates;
		
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(textureCoordinates.length * 4);
		byteBuf.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuf.asFloatBuffer();
		textureBuffer.put(textureCoordinates);
		textureBuffer.position(0);
	}
}
