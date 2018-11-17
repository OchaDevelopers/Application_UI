package com.search.prototype;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class search {
	public static void main(String[] args) throws IllegalArgumentException, IOException, ParseException {
		String indexDir = "C:\\Gitrepo\\index";
		String q = "tom";
		
		search(indexDir, q);
	}
	public static void search(String indexDir, String q) throws IOException, ParseException {
		Directory dir = FSDirectory.open(Paths.get(indexDir));
		
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher is =  new IndexSearcher(reader);
		
		QueryParser parse = new QueryParser("contents",	new StandardAnalyzer());
		
		Query query = parse.parse(q);
		long start = System.currentTimeMillis();
		TopDocs hits = is.search(query, 10);
		long end = System.currentTimeMillis();
		System.err.println("Found "+ hits.totalHits + " document(s)( in " + (end - start) + " milliseconds) that mached query '" + q+ "':" );
		
		for(ScoreDoc scoreDoc : hits.scoreDocs){
			Document doc = is.doc(scoreDoc.doc);
			System.out.println(doc.get("fullpath")+" sum : "+doc.toString());
		}
		reader.close();
	}
}
