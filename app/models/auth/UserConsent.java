package models.auth;

import models.core.TimeStamped;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Table(name = "user_consent", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "type"})})
public class UserConsent extends TimeStamped {

    @ManyToOne
    public User user;
    public String anonymousId;
    public String ipAddress;
    public Date acceptedAt;
    public Date revokedAt;
    public ConsentType type;
    public String policyVersion;
    public ConsentStatus status = ConsentStatus.PENDING;

    public static UserConsent findOrCreate(final User user, final String anonId) {
        UserConsent consent = null;
        if (user != null) {
            consent = UserConsent.find("user = ?1", user).first();
            if (consent != null) {
                if (anonId != null) {
                    consent.anonymousId = anonId;
                }
                return consent;
            }
            consent = new UserConsent();
            consent.user = user;
            consent.anonymousId = anonId;
            return consent;
        }
        if (anonId != null) {
            consent = UserConsent.find("anonymousId = ?1", anonId).first();
            if (consent != null) {
                return consent;
            }
        }
        consent = new UserConsent();
        consent.user = null;
        consent.anonymousId = anonId;
        return consent;
    }

    public static void linkAnonConsentToUser(final User user, String anonymousId, boolean consentAccepted) {
        UserConsent consent = UserConsent.find("user = ?1", user).first();
        if (consent != null) {
            if (!anonymousId.equals(consent.anonymousId)) {
                consent.anonymousId = anonymousId;
                consent.save();
                delete("anonymousId = ?1 AND user IS NULL", anonymousId);
            }
            return;
        }
        if (consentAccepted) {
            consent = find("anonymousId = ?1", anonymousId).first();
            if (consent != null) {
                consent.user = user;
                consent.save();
            } else {
                consent = new UserConsent();
                consent.user = user;
                consent.anonymousId = anonymousId;
                consent.type = ConsentType.COOKIES;
                consent.status = ConsentStatus.ACCEPTED;
                consent.save();
            }
        }
    }


}
