package org.dpppt.switzerland.backend.sdk.config.ws;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.dpppt.switzerland.backend.sdk.config.ws.semver.Version;
import org.junit.Test;

public class VersionTest {
    @Test
    public void testVersionFromString() throws Exception {
        var cases = List.of(new Version("0.1.0"),new Version("0.1.1"),new Version("0.2.0"),new Version("1.0.0-prerelease"),new Version("1.0.0"),new Version("1.0.1+ios"));
        for(int i =0; i < cases.size(); i++) {
            var currentVersion = cases.get(i);
            assertTrue(currentVersion.isSameVersionAs(currentVersion));
            for(int j = 0; j < i; j++ ){
                var olderVersion = cases.get(j);
                assertTrue(currentVersion.isLargerVersionThan(olderVersion));
            }
        }
        var releaseVersion = new Version("1.0.0");
        var metaInfoVersion = new Version("1.0.0+ios");
        assertTrue(releaseVersion.isSameVersionAs(metaInfoVersion));
        assertFalse(releaseVersion.equals(metaInfoVersion));
        var sameIosVersion = new Version("1.0.0+ios");
        assertTrue( metaInfoVersion.equals(sameIosVersion));
    }
    @Test
    public void testInvalidVersion() throws Exception {
        var invalid = new Version("ios-1.0.0");
        assertFalse(invalid.isValid());
    }
    @Test
    public void testVersionFromExplicit() throws Exception {
        var cases = List.of(new Version(0,1,0),new Version(0,1,1),new Version(0,2,0),new Version(1,0,0,"prerelease", ""),new Version(1,0,0),new Version(1,0,1,"", "ios"));
        for(int i =0; i < cases.size(); i++) {
            var currentVersion = cases.get(i);
            assertTrue(currentVersion.isSameVersionAs(currentVersion));
            for(int j = 0; j < i; j++ ){
                var olderVersion = cases.get(j);
                assertTrue(currentVersion.isLargerVersionThan(olderVersion));
            }
        }
        var releaseVersion = new Version(1,0,0);
        var metaInfoVersion = new Version(1,0,0,"", "ios");
        assertTrue(releaseVersion.isSameVersionAs(metaInfoVersion));
        assertFalse(releaseVersion.equals(metaInfoVersion));
        var sameIosVersion = new Version(1,0,0,"", "ios");
        assertTrue( metaInfoVersion.equals(sameIosVersion));
    }
}