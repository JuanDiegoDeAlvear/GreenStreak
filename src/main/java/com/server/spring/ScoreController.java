package com.server.spring;

import com.client.logic.RankingEntry;
import com.server.database.DatabaseProfileManager;
import com.server.endpoints.ScoreEndpoints;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ScoreEndpoints.SCORE)
public class ScoreController {

    @GetMapping(value = ScoreEndpoints.GET_RANKING_FRIEND)
    public String[] getFriendRanking() {
        return null;
    }

    /**
     * Returns RankingEntru[] of global ranking ordered by score.
     *
     * @return Returns RankingEntry[].
     */
    @GetMapping(value = ScoreEndpoints.GET_RANKING_GLOBAL_SCORE)
    public RankingEntry[] getGlobalRankingScore() {
        return DatabaseProfileManager.getInstance().getGlobalRanking("score");
    }

    /**
     * Returns RankingEntru[] of global ranking ordered by carbon footprint.
     *
     * @return Returns RankingEntry[].
     */
    @GetMapping(value = ScoreEndpoints.GET_RANKING_GLOBAL_CARBONPRINT)
    public RankingEntry[] getGlobalRankingCarbon() {
        return DatabaseProfileManager.getInstance().getGlobalRanking("footprint");
    }

    /**
     * Returns RankingEntru[] of global ranking ordered by eco footprint.
     *
     * @return Returns RankingEntry[].
     */
    @GetMapping(value = ScoreEndpoints.GET_RANKING_GLOBAL_ECOPRINT)
    public RankingEntry[] getGlobalRankingEco() {
        return DatabaseProfileManager.getInstance().getGlobalRanking("ecofootprint");
    }

    /**
     * Returns RankingEntru[] of friends ordered by eco score.
     *
     * @return Returns RankingEntry[].
     */
    @GetMapping(value = ScoreEndpoints.GET_RANKING_FRIEND_SCORE)
    public RankingEntry[] getFriendRankingScore(@RequestParam String username) {
        return DatabaseProfileManager.getInstance().getFriendRanking(username, "score");
    }

    /**
     * Returns RankingEntru[] of friends ordered by carbon footprint.
     *
     * @return Returns RankingEntry[].
     */
    @GetMapping(value = ScoreEndpoints.GET_RANKING_FRIEND_CARBONPRINT)
    public RankingEntry[] getFriendRankingCarbon(@RequestParam String username) {
        return DatabaseProfileManager.getInstance().getFriendRanking(username, "footprint");
    }

    /**
     * Returns RankingEntru[] of friends ordered by eco footprint.
     *
     * @return Returns RankingEntry[].
     */
    @GetMapping(value = ScoreEndpoints.GET_RANKING_FRIEND_ECOPRINT)
    public RankingEntry[] getFriendRankingEco(@RequestParam String username) {
        return DatabaseProfileManager.getInstance().getFriendRanking(username, "ecofootprint");
    }
}
