/*
 * European Variation Archive (EVA) - Open-access database of all types of genetic
 * variation data from all species
 *
 * Copyright 2020 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.ebi.eva.dbsnpimporter.configuration;

import com.mongodb.AuthenticationMechanism;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

import uk.ac.ebi.eva.dbsnpimporter.configuration.mongo.SpringDataMongoDbProperties;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBAdaptorConnector {

    /**
     * Get a MongoClient using the configuration (credentials) in a given Properties.
     *
     * @param springDataMongoDbProperties can have the next values:
     *                   - eva.mongo.auth.db authentication database
     *                   - eva.mongo.host comma-separated strings of colon-separated host and port strings: host_1:port_1,host_2:port_2
     *                   - eva.mongo.user
     *                   - eva.mongo.passwd
     *                   - eva.mongo.read-preference string, "secondaryPreferred" if unspecified. one of:
     *                          [primary, primaryPreferred, secondary, secondaryPreferred, nearest]
     * @return MongoClient with given credentials
     * @throws UnknownHostException
     */
    public static MongoClient getMongoClient(
            SpringDataMongoDbProperties springDataMongoDbProperties) throws UnknownHostException {

        String host = springDataMongoDbProperties.getHost();
        int port = springDataMongoDbProperties.getPort();
        port = (port == 0 ? 27017:port);
        List<ServerAddress> servers = new ArrayList<>();
        servers.add(new ServerAddress(host, port));

        String readPreference = springDataMongoDbProperties.getReadPreference();
        readPreference = readPreference == null || readPreference.isEmpty()? "secondaryPreferred" : readPreference;

        MongoClientOptions options = MongoClientOptions.builder()
                                                       .readPreference(ReadPreference.valueOf(readPreference))
                                                       .build();

        List<MongoCredential> mongoCredentialList = new ArrayList<>();
        String authenticationDb = springDataMongoDbProperties.getAuthenticationDatabase();
        if (authenticationDb != null && !authenticationDb.isEmpty()) {
            MongoCredential mongoCredential = MongoCredential.createCredential(
                    springDataMongoDbProperties.getUsername(),
                    authenticationDb,
                    springDataMongoDbProperties.getPassword().toCharArray());
            String authenticationMechanism = springDataMongoDbProperties.getAuthenticationMechanism();
            if (authenticationMechanism == null) {
                return new MongoClient(servers, options);
            }
            mongoCredential = mongoCredential.withMechanism(
                    AuthenticationMechanism.fromMechanismName(authenticationMechanism));
            mongoCredentialList = Collections.singletonList(mongoCredential);
        }

        return new MongoClient(servers, mongoCredentialList, options);
    }
}