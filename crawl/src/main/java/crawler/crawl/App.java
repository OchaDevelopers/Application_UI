package crawler.crawl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.TreeWalk;
public class App 
{
	/**
	 * @param args
	 * @throws IOException 
	 * @throws GitAPIException 
	 * @throws NoHeadException 
	 */
	public static void main(String[] args) throws IOException, NoHeadException, GitAPIException {
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		Repository repository = builder.setGitDir(new File("C:\\Gitrepo\\farsec\\derby\\.git"))
		  .readEnvironment() // scan environment GIT_* variables
		  .findGitDir() // scan up the file system tree
		  .build();
		FileWriter fw = new FileWriter("D:\\program file\\result\\gitlog.txt");
		BufferedReader br = new BufferedReader(new FileReader("D:\\Program file\\result\\title.txt"));
		String title[] = new String[15];
		SimpleDateFormat orgin = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		SimpleDateFormat newone = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String temp ="";
		int year = 0;
		int k = 0;
		int j = 0;
		int tag = 0;
		for(int i = 0; i < 15; i++) {
			title[i] = br.readLine();
			if(title[i] == null) break;
		}
		Git git = new Git(repository);
		Iterator<RevCommit> commitLogs = git.log().call().iterator();
		for (;commitLogs.hasNext();k++) {
			//System.out.println(k); //check the end
			RevCommit currentCommit = commitLogs.next();
			RevCommit parentCommit = currentCommit.getParent(0);
			String fixedCommitId = (currentCommit.getName());
			// prepare the two iterators to compute the diff between
			ObjectReader reader = repository.newObjectReader();
			CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
			oldTreeIter.reset(reader, parentCommit.getTree().getId());
			CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
			newTreeIter.reset(reader, currentCommit.getTree().getId());
			long timestamp = (long) currentCommit.getCommitTime() * 1000;
			Date commitTime = new Date(timestamp);
			/*System.out.printf("Committer: %s, "
					+ ": %s, Msg: %s\n",
					currentCommit.getCommitterIdent().getName(),
					commitTime.toString(),
					currentCommit.getShortMessage());*/
			for(j = 0; j < 15; j++) {
				if (title[j] == null) break;
				if (currentCommit.getShortMessage().contains(title[j])) {
					tag = 1;
					fw.write("title:"+title[j]+"\n");
					break;
				}
			}
			if (tag == 0) {
				continue;
			}
			tag = 0;
			//System.out.printf("Commit ID: %s\n", currentCommit.getId().name());
			fw.write("CommitID:"+currentCommit.getId().name()+"\n");
			fw.write("author:"+currentCommit.getAuthorIdent().getName()+"\n");
			try {
				date = orgin.parse(commitTime.toString());
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			fw.write("time:"+newone.format(date) +"\n");
			// finally get the list of changed files
			List<DiffEntry> diffs = new Git(repository).diff().setNewTree(newTreeIter).setOldTree(oldTreeIter).call();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			DiffFormatter df = new DiffFormatter(out);
			df.setRepository(git.getRepository());
			ObjectId oldId = git.getRepository().resolve(fixedCommitId + "~1^{tree}");
			ObjectId headId = git.getRepository().resolve(fixedCommitId + "^{tree}");
			ObjectReader newObjectReader = git.getRepository().newObjectReader();
			for (DiffEntry entry : diffs) {
				//System.out.printf("ChagngeType: %s, Path: %s\n", entry.getChangeType().toString(), entry.getPath(DiffEntry.Side.NEW));
				fw.write("path:"+entry.getPath(DiffEntry.Side.NEW)+"\n");
				/*temp = entry.getPath(DiffEntry.Side.NEW);
				
				if(temp.endsWith(".java")) {
					fw.write("path:"+temp+"\n");
				} else {
					fw.write("");
				}*/ // 왜 안될까?
				//System.out.println(entry.getPath(DiffEntry.Side.NEW));
				//System.out.println(entry);
				df.format(entry);
				String diffText = out.toString("UTF-8");
				int chunkHeaderIndex = diffText.indexOf("@@");
				// in case of deleted file
				if (chunkHeaderIndex == -1) {
					continue;
				}
				RevTree tree = currentCommit.getTree();
				String oldPath = entry.getOldPath();
				String newPath = entry.getNewPath();
				TreeWalk treeWalk = TreeWalk.forPath(newObjectReader, newPath, tree);
				if (newPath.endsWith(".java")) {
					//System.out.println("FileName : " + newPath);
					if (newPath != null) {
						String methodData = diffText.substring(chunkHeaderIndex);
						BufferedReader chunk = new BufferedReader(new StringReader(methodData));
						String line = null;
						int chunkStartLineNum = 0;
						int chunkLineCount = 0;
						while ((line = chunk.readLine()) != null) {
							System.out.println(line); // diff 메세지 출력
							//fw.write(line);
						}
					}
				}
			}
		}
		fw.close();
		repository.close();
		System.out.println("\nend");
	}
}