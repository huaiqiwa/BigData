//1GB memory
public int findMissingNumber(int[] data) {
	long n = (long)Integer.MAX_VALUE << 1;
	byte[] bitmap = new byte[(int)(n>>3)];
	for(int i = 0; i < data.length; i++) {
		bitmap[data[i]>>3] |= (1 << (data[i]&7));
	}
	for(int i = 0; i < bitmap.length; i++) {
		for(int j = 0; j < 8; j++) {
			if((bitmap[i] & (1<<j)) == 0) return i*8+j;
		}
	}
	return -1;
}

//10MB memory
public int findMissingNumber(int[] data) {
	int blockSize = 1 << 18;
	int blockNum = 1 << 14;
	int start = countNumInBlock(data, new int[blockNum], blockSize);
	return start+findMissingNumInBlock(data, start, blockSize);
}

private int countNumInBlock(int[] data, int[] counts, int blockSize) {
	for(int i = 0; i < data.length; i++) {
		counts[data[i]>>18]++;
	}
	for(int i = 0; i < counts.length; i++) {
		if(counts[i] < blockSize) return blockSize*i;
	}
	return -1;
}

private int findMissingNumInBlock(int[] data, int start, int blockSize) {
	byte[] bitmap = new byte[(int)(blockSize>>3)];
	int end = start + blockSize;
	for(int i = 0; i < data.length; i++) {
		if(data[i] >= start && data[i] < end) {
			bitmap[data[i]>>3] |= (1 << (data[i]&7));
		}
	}
	for(int i = 0; i < bitmap.length; i++) {
		for(int j = 0; j < 8; j++) {
			if((bitmap[i] & (1<<j)) == 0) return (i*8+j);
		}
	}
	return -1;
}













