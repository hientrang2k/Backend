package com.globits.Backend.domain;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.globits.core.domain.BaseObject;

@Entity
@Table(name = "tbl_video")
public class UploadVideo extends BaseObject  {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Column(name = "original_path")
    @Type(type = "text")
    public String originalFilePath;

    @Column(name = "original_file_name")
    @Type(type = "text")
    public String originalFileName;

    @Column(name = "original_file_ext")
    public String originalFileExtension;
    
    @Column(name = "title")
    @Type(type = "text")
    public String title;
    
    @Column(name = "descriptions")
    @Type(type = "text")
    public String descriptions;
   
    
//    @OneToMany(mappedBy = "uploadVideo")
//    private List<RelatedArticleVideo> relatedArticleVideo;
 

	public UploadVideo(UUID id) {
		super();
	}

	public UploadVideo(String originalFilePath, String originalFileName, String originalFileExtension, String descriptions, String title) {
		super();
		this.originalFilePath = originalFilePath;
		this.originalFileName = originalFileName;
		this.originalFileExtension = originalFileExtension;
		this.descriptions = descriptions;
		this.title = title;
	}

	public UploadVideo() {
		super();
	}
//
//	public List<RelatedArticleVideo> getRelatedArticleVideo() {
//		return relatedArticleVideo;
//	}
//
//	public void setRelatedArticleVideo(List<RelatedArticleVideo> relatedArticleVideo) {
//		this.relatedArticleVideo = relatedArticleVideo;
//	}

	public String getOriginalFilePath() {
		return originalFilePath;
	}

	public void setOriginalFilePath(String originalFilePath) {
		this.originalFilePath = originalFilePath;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getOriginalFileExtension() {
		return originalFileExtension;
	}

	public void setOriginalFileExtension(String originalFileExtension) {
		this.originalFileExtension = originalFileExtension;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	

    
    

}
