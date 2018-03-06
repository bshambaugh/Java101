package functionjava10_c;

/// Code from: https://crunchify.com/how-to-retry-operation-n-number-of-times-in-java/

public class CrunchifyRetryOnExceptionStrategy {
	public static final int DEFAULT_RETRIES = 1;
	public static final long DEFAULT_WAIT_TIME_IN_MILLI = 2000;

	private int numberOfRetries;
	private int numberOfTriesLeft;
	private long timeToWait;

	public CrunchifyRetryOnExceptionStrategy() {
		this(DEFAULT_RETRIES, DEFAULT_WAIT_TIME_IN_MILLI);
	}

	public CrunchifyRetryOnExceptionStrategy(int numberOfRetries,
			long timeToWait) {
		this.numberOfRetries = numberOfRetries;
		numberOfTriesLeft = numberOfRetries;
		this.timeToWait = timeToWait;
	}

	/**
	 * @return true if there are tries left
	 */
	public boolean shouldRetry() {
		return numberOfTriesLeft > 0;
	}

	public void errorOccured() throws Exception {
		numberOfTriesLeft--;
		if (!shouldRetry()) {
			throw new Exception("Retry Failed: Total " + numberOfRetries
					+ " attempts made at interval " + getTimeToWait()
					+ "ms");
		}
		waitUntilNextTry();
	}

	public long getTimeToWait() {
		return timeToWait;
	}

	private void waitUntilNextTry() {
		try {
			Thread.sleep(getTimeToWait());
		} catch (InterruptedException ignored) {
		}
	}
}
