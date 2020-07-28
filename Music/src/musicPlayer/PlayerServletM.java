package musicPlayer;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.DecoderException;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

public class PlayerServletM implements Runnable  {
	
	private Thread thisThread;
	
	final static int STATE_STARTED = 0;
	final static int STATE_STOPPED = 1;
	static int stateCode = STATE_STOPPED;
	
	public static final int BUFFER_SIZE = 10000;
	
	private Decoder decoder;
	private AudioDevice out;
	private ArrayList<Sample> playes;
	private int length;
	private String name;
	
	public PlayerServletM(String name) {
		stateCode = STATE_STARTED;
		this.name = name;
	}
	
	public void start() {
		System.out.println("시작");
		synchronized (this) {
			System.out.println("스레드 만들기");
			thisThread = new Thread(this);
			thisThread.setDaemon(true);
			thisThread.start();
		}
	}
	
	@Override
	public void run() {
		System.out.println("런");
		while(true) {
			System.out.println("run while");
			if(stateCode == STATE_STOPPED) {  // 상태를 판단. stopped가 되면 break;되면서 스레드가 종료						
				break;
			}
			play(); //run안에서 play 시작 //상태별로 스레드는 실행하고 있지만 mp3 재생 상태가 달라짐.
		}
	}
	
	static class Sample {
		private short[] buffer;
		private int length;
		
		public Sample(short[] buf, int s) {
			buffer = buf.clone();
			length = s;
		}

		public short[] getBuffer() {
			return buffer;
		}

		public void setBuffer(short[] buffer) {
			this.buffer = buffer;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}
	}
	
	public boolean Open(String path) { //재생환경 세팅.
		try {
			System.out.println("오픈");
			decoder = new Decoder();
			out = FactoryRegistry.systemRegistry().createAudioDevice();
			playes = new ArrayList<Sample>(BUFFER_SIZE);
			length = 0;
			out.open(decoder);
			Getplayes(path);
		}catch (JavaLayerException e) {
			System.out.println("createAudioDevice 또는 out.open 오류");
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	protected boolean Getplayes(String path) { //파일경로 지정, 파일스트림
//		if(IsInvalid()) { return false; }
		try {
			System.out.println("겟플레이스");
			Header header;
			SampleBuffer pb;
			FileInputStream in = new FileInputStream(path);
			Bitstream bitstream = new Bitstream(in);
			if((header=bitstream.readFrame()) == null ) { return false; }
			while(length <BUFFER_SIZE && header != null) {
				pb = (SampleBuffer) decoder.decodeFrame(header, bitstream);
				playes.add(new Sample(pb.getBuffer(), pb.getBufferLength()));
				length++;
				bitstream.closeFrame();
				header = bitstream.readFrame();
			}
		} catch(Exception e) { return false; }
		return true;
	}
	
//	public boolean IsInvalid() { // 재생 환경 판단
//		if(decoder == null) {System.out.println("디코더 없음");}
//		if(out == null) { System.out.println("out없음"); }
//		if(playes == null) { System.out.println("플레이즈 없음"); }
//		if(!out.isOpen()) { System.out.println("out이 안 열려있음"); }
//		
//		
//		return (decoder == null || out == null || playes == null || !out.isOpen());
//	}
	
	public void play() {
		
		Open("c://upload/"+name+".mp3");
		System.out.println("c://upload/"+name+".mp3");
		System.out.println("음악시작");
		try {
			for(int i=0; i<length; i++) {
				out.write(playes.get(i).getBuffer(), 0, playes.get(i).getLength());
				if(stateCode == STATE_STOPPED) { //STOPPED 상태면 재생환경 닫기
					Close(); 
				}
			}
		}catch (JavaLayerException e) { }
		Close(); // 종료 전 재생환경 닫기
	}
	
	public void Close() {
		stateCode = STATE_STOPPED;
		if((out != null) && !out.isOpen()) {
			out.close();
		}
		length = 0;
		playes = null;
		out = null;
		decoder = null;
	}
	
	public void stop() {
		synchronized (this) {
			this.stateCode = STATE_STOPPED;
			try {
				thisThread.sleep(100);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
