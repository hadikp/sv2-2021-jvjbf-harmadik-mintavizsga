package streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VideoService {

    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video) {
        videos.add(video);
    }

    public List<Video> findVideosByTitle(String titlePart) {
        return videos.stream().filter(f -> f.getTitle().contains(titlePart))
                .sorted(Comparator.comparing(Video::getUploadDate).reversed()).toList();
    }

    public long countVideosWithHashTag(String hashTag) {
        return videos.stream().filter(f -> f.getHashTags().contains(hashTag)).count();
    }

    public Video firstVideoByDate() {
        return videos.stream().sorted(Comparator.comparing(Video::getUploadDate)).findFirst()
                .orElseThrow(() -> new IllegalStateException("No videos in list!"));
    }

    public long sumOfVideoLengths() {
        return videos.stream().mapToLong(f -> f.getLength()).sum();
    }
}
