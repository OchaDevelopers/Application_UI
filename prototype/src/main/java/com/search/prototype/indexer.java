package com.search.prototype;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class indexer {
	private IndexWriter writer;
	
	public indexer(String indexDir) throws IOException {
		Directory dir =FSDirectory.open(Paths.get(indexDir));
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig();
		writer = new IndexWriter(dir, config);
	}
	public static void main(String[] args) throws Exception {
		String indexDir = "C:\\Gitrepo\\index";
		String dataDir = "C:\\Gitrepo\\test";
		long start = System.currentTimeMillis();
		indexer indexer = new indexer(indexDir);
		int numIndexed;
		
		try {
			numIndexed = indexer.index(dataDir, new TextFilesFilter());
		} finally {
			indexer.close();
		}
		long end = System.currentTimeMillis();
		
		System.out.println("indexed number" + numIndexed);
		System.out.println(end-start);
	}
	public void close() throws IOException {
        writer.close();
    }
    public int index(String dataDir, FileFilter filter) throws Exception {
    	File[] files = new File(dataDir).listFiles();
        for(File f:files) {
            System.out.println(f.getName());
            if(!f.isDirectory() && !f.isHidden() && f.exists() && f.canRead() && (filter == null || filter.accept(f))) {
                indexFile(f);
            }
        }
        return writer.numDocs();
    }
    private void indexFile(File f) throws Exception {
        System.out.println("Indexing" + f.getCanonicalPath());
        Document doc = getDocument(f);
        writer.addDocument(doc);
    }
    protected Document getDocument(File f) throws Exception {
        Document doc = new Document();
        doc.add(new TextField("contents", new FileReader(f)));
        doc.add(new TextField("filename", f.getName(), Field.Store.YES));
        doc.add(new StringField("fullpath", f.getCanonicalPath(), Field.Store.YES));
        return doc;
    }
    private static class TextFilesFilter implements FileFilter{
        @Override
        public boolean accept(File pathname) {
        return pathname.getName().toLowerCase().endsWith(".txt");
        }
    }
}
