package net.mgsx.ppp.midi.pd;

import net.mgsx.ppp.midi.MidiCode;
import net.mgsx.ppp.midi.MidiOutput;
import net.mgsx.ppp.pd.PdClock;

import org.puredata.core.PdBase;

public class PdMidiOutput implements MidiOutput
{
	private int port;
	
	public PdMidiOutput(int port) {
		super();
		this.port = port;
	}

	@Override
	public void open() {
		// nothing to do
	}

	@Override
	public void close() {
		// nothing to do
	}

	@Override
	public void send(byte[] message) 
	{
		send(port, message);
	}

	@Override
	public String getName() {
		return "Midi " + String.valueOf(port + 1);
	}

	public static void send(int port, byte[] message) 
	{
		if(message.length == 1)
		{
			int first = message[0] & 0xFF;
			if(MidiCode.isRealtime(first))
			{
				PdBase.sendSysRealTime(port, first);
			}
			else
			{
				System.err.println("Unsupported non realtime one byte message : " + first);
			}
		}
		else if(message.length == 3)
		{
			PdBase.sendList(PdClock.ClockMidiInputReceiver,
				Integer.valueOf(message[0] & 0xFF), 
				Integer.valueOf(message[1] & 0xFF),
				Integer.valueOf(message[2] & 0xFF));
		}
		else
		{
			System.err.println("Unsupported message length : " + message.length);
		}
	}

}
