package at.fb.portfolio;

import min3d.core.Object3dContainer;
import min3d.core.RendererActivity;
import min3d.interfaces.ISceneController;
import min3d.parser.IParser;
import min3d.parser.Parser;
import min3d.vos.Light;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.MotionEvent;
import at.fb.portfolio.projectItems.ProjectItemObj;

public class ObjActivity extends RendererActivity implements ISceneController {
	private Object3dContainer objModel;

	@Override
	public void initScene() {

		Bundle extras = getIntent().getExtras();

		Light l1 = new Light();
		l1.position.setAll(3, 3, 3);

		scene.lights().add(l1);
		scene.backgroundColor().setAll(0, 0, 0, 0);

		IParser parser = Parser.createParser(Parser.Type.OBJ, getResources(),
				extras.getString(ProjectItemObj.PATH), true);
		parser.parse();

		objModel = parser.getParsedObject();
		objModel.scale().x = objModel.scale().y = objModel.scale().z = .1f;
		scene.addChild(objModel);
	}

	@Override
	public void updateScene() {
		objModel.rotation().y++;
	}

	@Override
	protected void glSurfaceViewConfig() {
		_glSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		_glSurfaceView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		_glSurfaceView.setZOrderOnTop(true);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		finish();
		return super.dispatchTouchEvent(ev);
	}

}
