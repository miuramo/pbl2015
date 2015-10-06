package sudoku;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.swing.JFileChooser;

public class AbstractDataStore {


	public static String selectfile(boolean issave, String ext){
		//		ファイルダイアログを開く
		String cdir = System.getProperty("user.dir");
		JFileChooser fd = new JFileChooser(cdir);
		NullFileFilter nff = new NullFileFilter(ext);
		fd.setFileFilter(nff);
		int returnval;
		if (issave) returnval = fd.showSaveDialog(null);
		else returnval = fd.showOpenDialog(null);
		if (returnval == JFileChooser.APPROVE_OPTION){
			// 拡張子がついていないとき，むりやりつける
			StringBuffer path = new StringBuffer(fd.getSelectedFile().getAbsolutePath());
			if (!path.toString().endsWith(ext)){
				path.append(ext);
			}
			return path.toString();
		}else{
			return null;
		}
	}
	public static <E> void saveToFile(String fn, ArrayList<E> ary, String dotextension) {
		if (fn == null) fn = selectfile(true, dotextension /*".satn"*/);
		if (fn == null) return;//もしファイル名を選択しなかったら
		FileReadWriter.writeBytesToFile(fn, byteSerializeExport(ary));
		File f = new File(fn);
		System.out.println(f.getAbsolutePath()+"\nに保存しました");
	}
	public static <E> ArrayList<E> loadFromFile(String fn, String dotextension){
		if (fn == null) fn = selectfile(false, dotextension /*".satn"*/);
		if (fn == null) return null;//もしファイル名を選択しなかったら
		byte[] ba = FileReadWriter.readBytesFromFile(fn);
		File f = new File(fn);
		System.out.println(f.getAbsolutePath()+"\nから読み込みました");
		return byteSerializeImport(ba);
	}
	
	public static <E> byte[] byteSerializeExport(ArrayList<E> ary) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		OutputStream gzipos = null;
		ObjectOutputStream pobjos = null; // PNodeを保存するときはPObjectOutputStream

		try {
			gzipos = new GZIPOutputStream(baos);
			pobjos = new ObjectOutputStream(gzipos);
			pobjos.writeObject(ary);
			pobjos.close();
			gzipos.close();
		} catch (IOException iex) {
			iex.printStackTrace(System.out);
		}
		System.out.println("save size: " + baos.size() + " bytes.");
		return baos.toByteArray();
	}
	
	@SuppressWarnings("unchecked")
	public static <E> ArrayList<E> byteSerializeImport(byte[] ba){
		ByteArrayInputStream bais = new ByteArrayInputStream(ba);
		InputStream gzipis = null;
		ObjectInputStream ois = null;
		ArrayList<E> ret = null;
		try {
			gzipis = new GZIPInputStream(bais);
			ois = new ObjectInputStream(gzipis);
			ret = (ArrayList<E>) ois.readObject();
			ois.close();
			gzipis.close();
			bais.close();
		} catch (EOFException eofex) {
			eofex.printStackTrace();
		} catch (IOException excep) {
			excep.printStackTrace(System.err);
		} catch (ClassNotFoundException excnf) {
			System.err.println("ClassNotFound Error");
		} finally {
		}
		return ret;
	}
	
	

}
