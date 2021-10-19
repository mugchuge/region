package com.io.region.region.implement;

import com.io.region.region.api.Regions;
import org.bukkit.entity.Player;

public class RegionImp implements Regions {

    @Override
    public void registerNewRegion(String region, double[] minBox, double[] maxBox) {
        regions.add(region);
        regionMinBox.put(region, minBox);
        regionMaxBox.put(region, maxBox);
    }

    @Override
    public boolean hasRegion(String region) {
        return regions.contains(region);
    }

    @Override
    public void giveOwnerInRegion(String region, Player owner) {
        if (hasRegion(region)) {
            regionOwner.put(region, owner);
        }
    }

    @Override
    public boolean isOwner(String region, Player owner) {
        return regionOwner.get(region).equals(owner);
    }

    @Override
    public String getRegion(Player owner) {
        for (String region : regions) {
            if (regionOwner.get(region).equals(owner)) {
                return region;
            }
        }
        return null;
    }

    @Override
    public boolean contains(String region, double[] loc) { // locX, locY, locZ
        double[] minBox = regionMinBox.get(region); // minX, minY, minZ
        double[] maxBox = regionMaxBox.get(region); // maxX, minY, minZ
        return (((minBox[0] < loc[0] && maxBox[0] > loc[0]) && (minBox[1] < loc[1] && maxBox[1] > loc[1]) && (minBox[2] < loc[2] && maxBox[2] > loc[2])));
    }

    @Override
    public void removeRegion(String region) {
        if (hasRegion(region)) {
            regions.remove(region);
            double[] minBox = regionMinBox.get(region);
            regionMinBox.put(region, minBox);
            double[] maxBox = regionMaxBox.get(region);
            regionMaxBox.remove(region, maxBox);
        }
    }

    @Override
    public void relocateRegion(String region, double[] newMinBox, double[] newMaxBox) {
        if (hasRegion(region)) {
            double[] minBox = regionMinBox.get(region);
            regionMinBox.replace(region, minBox, newMinBox);
            double[] maxBox = regionMaxBox.get(region);
            regionMaxBox.replace(region, maxBox, newMaxBox);
        }
    }
}
