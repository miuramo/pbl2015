package sudoku;
import java.io.File;

/**
 * Java�̃t�@�C���_�C�A���O�iJFileChooser�j�ŁC����̊g���q�̃t�@�C���݂̂�
 * �I��ŕ\���������Ƃ��ɁC�p����D
 * 
 * �g���q�������R���X�g���N�^�Ŏw��ł��邽�߁C���݂��g�p���D
 * 
 * @author miuramo
 *
 */
public class NullFileFilter extends javax.swing.filechooser.FileFilter implements
		java.io.FilenameFilter {
	String suffix;

	/**
	 * �R���X�g���N�^
	 * @param s �g���q�̕�����(��Ctxt)
	 */
	public NullFileFilter(String s) {
		suffix = s;
	}

	public boolean accept(File dir) {
		String name = dir.getName();
		if (name.endsWith(suffix) || dir.isDirectory()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean accept(File dir, String name) {
		if (name.endsWith(suffix)) {
			return true;
		} else {
			return false;
		}
	}

	public String getDescription() {
		return suffix;
	}
}
