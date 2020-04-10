package com.gaf.reminderserver.models;

import java.io.File;

public class EmailAttachment extends BaseData  {
	/**
	 * attachment
	 */
	private File attachment;

	/**
	 * Public Constructor
	 * 
	 * @param fileName
	 * @throws IllegalArgumentException
	 */
	public EmailAttachment(final String fileName)
			throws IllegalArgumentException {
		if (fileName == null) {
			throw new IllegalArgumentException("Filename is null");
		}

		this.attachment = getFileObject(fileName);
	}

	public EmailAttachment(final File attachment) throws IllegalArgumentException {
		if (attachment == null || !attachment.isFile()) {
			throw new IllegalArgumentException("No File specified");
		}

		this.attachment = attachment;
	}

	/**
	 * Constructs a file object
	 * 
	 * @param fileName
	 *            the name of the file on the server
	 * @return File a file object
	 */
	private File getFileObject(final String fileName) {
		String attachmentPath = null;
		File f = new File(attachmentPath + File.separator + fileName);

		return f;
	}

	/**
	 * Returns the attachment
	 * 
	 * @return File
	 */
	public File getAttachment() {
		return attachment;
	}

	/**
	 * Sets the file attachment
	 * 
	 * @param attachment
	 */
	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

}
