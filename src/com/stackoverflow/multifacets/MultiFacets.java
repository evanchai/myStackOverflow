package com.stackoverflow.multifacets;

import java.util.ArrayList;
import java.util.List;

import org.carrot2.clustering.kmeans.BisectingKMeansClusteringAlgorithm;
import org.carrot2.clustering.lingo.LingoClusteringAlgorithm;
import org.carrot2.clustering.stc.STCClusteringAlgorithm;
import org.carrot2.clustering.synthetic.ByUrlClusteringAlgorithm;
import org.carrot2.core.Cluster;
import org.carrot2.core.Controller;
import org.carrot2.core.ControllerFactory;
import org.carrot2.core.Document;
import org.carrot2.core.ProcessingResult;
import org.jsoup.Jsoup;

import com.stackoverflow.bean.Post;
import com.stackoverflow.dao.PostDAOImpl;

public class MultiFacets {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PostDAOImpl postDAO= new PostDAOImpl();
		List<Post> postList = postDAO.findPosts("database");
		//clusteringByContentAndTag(postList);
		clusteringByProgarmmingLanuguage(postList);
	}
	
	public static void clusteringByContentAndTag(List<Post> postList) throws Exception
	{ 	
        /* Prepare Carrot2 documents */
        final ArrayList<Document> documents = new ArrayList<Document>();
        for (Post post : postList)
        {
        	if(post.post_typeId==1)
        		documents.add(new Document(post.post_title,post.parsePost_body_text(),"Description"));
        }

        /* A controller to manage the processing pipeline. */
        final Controller controller = ControllerFactory.createSimple();
        /*
         * Perform clustering by topic using the Lingo algorithm. Lingo can 
         * take advantage of the original query, so we provide it along with the documents.
         */
        final ProcessingResult byContentClusters = controller.process(documents, "database",
        		LingoClusteringAlgorithm.class);
        //BisectingKMeansClusteringAlgorithm,LingoClusteringAlgorithm,STCClusteringAlgorithm
        final List<Cluster> clustersByContent = byContentClusters.getClusters();
        
        documents.clear();
        for (Post post : postList)
        {
        	if(post.post_typeId==1)
        		documents.add(new Document(post.parsePost_tag(),"Summary",post.post_tag));
        }
        /* Perform clustering by domain. In this case query is not useful, hence it is null. */
        final ProcessingResult byTagClusters = controller.process(documents, null,
        		LingoClusteringAlgorithm.class);
        final List<Cluster> clustersByTag = byTagClusters.getClusters();
        // [[[end:clustering-document-list]]]
        
        //ConsoleFormatter.displayClusters(clustersByContent);
        ConsoleFormatter.displayClusters(clustersByTag);
	}
	public static void clusteringByProgarmmingLanuguage(List<Post> postList) throws Exception
	{
		String [] languageSet={"java","javascript","C#","php","jquery","python","html","C++","" +
				"css","objective-c","c","ruby","ajax","regex","xml","json","wpf","r"};
		
        /* Prepare Carrot2 documents */
        final ArrayList<Document> documents = new ArrayList<Document>();
        for (Post post : postList)
        {
        	if(post.post_typeId==1)
        		documents.add(new Document(post.post_title,post.post_body,post.post_tag));
        }
        
		List<Cluster> clustersByLanguage = new ArrayList<Cluster>();
		for (String language : languageSet)
		{
			Cluster cluster = new Cluster();
			for(Document document : documents)
			{
				if(!document.getTitle().isEmpty()&&(document.getTitle()+document.getContentUrl()+document.getSummary()).contains(language))
				{
					cluster.addDocument(document);
				}
			}
			if(cluster.getAllDocuments().size()>0) 
			{
				clustersByLanguage.add(cluster);
				cluster.addPhrases(language);
			}
        }
		
		//display 
		 System.out.println("\n\nCreated " + clustersByLanguage.size() + " clusters\n");
		for(Cluster cluster : clustersByLanguage)
		{
			System.out.println("\n\n"+cluster.getPhrases()+" clustering" + cluster.size() + " document\n");
		}
	}
}
