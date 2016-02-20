class User < ActiveRecord::Base
	def self.find_for_verfied_token_response(auth)
		user = User.where(:provider => "google_oauth2", :uid => auth[:id]).first

		unless user
			user = User.create(:name => auth[:name],
				:email => auth[:email],
				:provider => "google_oauth2",
				:uid => auth[:id])
			user.api_key = ApiKey.create!
		end

		user
	end
end
