package com.nixmash.wp.migrator.utils;

import com.nixmash.wp.migrator.db.local.dto.LocalUserDTO;
import com.nixmash.wp.migrator.db.local.model.LocalCategory;
import com.nixmash.wp.migrator.db.local.model.LocalPost;
import com.nixmash.wp.migrator.db.local.model.LocalTag;
import com.nixmash.wp.migrator.db.wp.model.WpCategory;
import com.nixmash.wp.migrator.db.wp.model.WpPost;
import com.nixmash.wp.migrator.db.wp.model.WpTag;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by daveburke on 1/20/17.
 */
public class ImportUtils {

    public static LocalPost wpToLocalPost(WpPost wpPost) {
        return LocalPost.getBuilder(
                wpPost.getUserId(),
                wpPost.getPostTitle(),
                wpPost.getPostName(),
                ZonedDateTime.ofInstant(wpPost.getPostDate().toInstant(), ZoneId.of("UTC")),
                ZonedDateTime.ofInstant(wpPost.getPostModified().toInstant(), ZoneId.of("UTC")),
                wpPost.getPostId())
                .build();
    }

    public static LocalTag wpToLocalTag(WpTag wpTag) {
        return LocalTag.getWpBuilder(wpTag.getTagValue(),
                wpTag.getWpTagId())
                .build();
    }

    public static LocalCategory wpToLocalCategory(WpCategory wpCategory) {
        return LocalCategory.getWpBuilder(wpCategory.getCategoryValue(),
                wpCategory.getWpCategoryId())
                .build();
    }

    public static long timeMark() {
        return new Date().getTime();
    }

    public static String totalTime(long lStartTime, long lEndTime) {
        double seconds = (lEndTime - lStartTime)/1000.0;
        return String.format("%4.2f seconds", seconds);
    }

    public static String bcryptedPassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    public static LocalUserDTO updateLocalUserDTO(String username, String email, String firstName, String lastName) {
        return LocalUserDTO.getBuilder(1L, username, email, firstName, lastName).build();
    }

    public static String clean(String html) {
        String out;
        out = html.replace("&#8217;", "&#39;");
        return out;
    }
}
