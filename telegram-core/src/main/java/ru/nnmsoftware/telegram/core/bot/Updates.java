package ru.nnmsoftware.telegram.core.bot;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import ru.nnmsoftware.browserrequest.request.BrowserRequest;
import ru.nnmsoftware.browserrequest.request.Method;
import ru.nnmsoftware.telegram.core.types.Update;

import java.util.List;

/**
 * Created by stille on 16.04.17.
 */
class Updates {

    private static final String GET_UPDATES = "getUpdates";

    private TelegramBot bot;
    private int offset = 0;

    Updates(TelegramBot bot) {
        this.bot = bot;
    }

    public List<Update> get() {
        BrowserRequest request = bot
                .basicRequest()
                .appendPath(GET_UPDATES)
                .withMethod(Method.POST)
                .withBody(
                        new Gson().toJson(
                                new UpdatesRequestBody().setOffset(offset + 1)
                        ).getBytes());
        return bot.getMultipleResponse(request, Update.class);
    }

    class UpdatesRequestBody {

        private int offset;
        private int limit;
        private int timeout;

        @SerializedName("allowed_updates")
        private String[] allowedUpdates;

        public UpdatesRequestBody setOffset(int offset) {
            this.offset = offset;
            return this;
        }

        public UpdatesRequestBody setLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public UpdatesRequestBody setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public UpdatesRequestBody setAllowedUpdates(String[] allowedUpdates) {
            this.allowedUpdates = allowedUpdates;
            return this;
        }
    }

}
